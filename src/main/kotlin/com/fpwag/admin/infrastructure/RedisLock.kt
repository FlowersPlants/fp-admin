package com.fpwag.admin.infrastructure

import com.fpwag.boot.autoconfigure.web.SpringContextHolder
import com.fpwag.boot.core.utils.IdUtils
import org.springframework.core.io.ClassPathResource
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.scripting.support.ResourceScriptSource
import java.util.*
import java.util.Collections.singletonList

/**
 * redis分布式锁（可重入锁，基于lua脚本）
 * 也叫做递归锁，指的是在同一线程内，外层函数获得锁之后，内层递归函数仍然可以获取到该锁。换一种说法：同一个线程再次进入同步代码时，可以使用自己已获取到的锁。可重入锁可以避免因同一线程中多次获取锁而导致死锁发生。像synchronized就是一个重入锁，它是通过moniter函数记录当前线程信息来实现的。实现可重入锁需要考虑两点：
　　　获取锁：首先尝试获取锁，如果获取失败，判断这个锁是否是自己的，如果是则允许再次获取， 而且必须记录重复获取锁的次数。
　　　释放锁：释放锁不能直接删除了，因为锁是可重入的，如果锁进入了多次，在内层直接删除锁， 导致外部的业务在没有锁的情况下执行，会有安全问题。因此必须获取锁时累计重入的次数，释放时则减去重入次数，如果减到0，则可以删除锁。
 *
 * @author fpwag
 */
object RedisLock {
    private val redisTemplate: RedisTemplate<String, Any> = SpringContextHolder.getBean("redisTemplate")
    private var LOCK_SCRIPT: DefaultRedisScript<Long> = DefaultRedisScript()
    private var UNLOCK_SCRIPT: DefaultRedisScript<Any> = DefaultRedisScript()

    init {
        // 加载获取锁的脚本
        LOCK_SCRIPT.setScriptSource(ResourceScriptSource(ClassPathResource("scripts/lock.lua")))
        LOCK_SCRIPT.resultType = Long::class.java

        // 加载释放锁的脚本
        UNLOCK_SCRIPT.setScriptSource(ResourceScriptSource(ClassPathResource("scripts/unlock.lua")))
    }

    /**
     * 获取锁
     *
     * @param lockName 锁名称
     * @param releaseTime 超时时间(单位:秒)
     * @return key 解锁标识
     */
    fun tryLock(lockName: String?, releaseTime: String?): String? {
        val key: String = IdUtils.uuid()
        // 执行脚本
        // 存入的线程信息的前缀，防止与其它JVM中线程信息冲突
        val result: Long = redisTemplate.execute(
                LOCK_SCRIPT,
                Collections.singletonList(lockName),
                key + Thread.currentThread().id, releaseTime)
        // 判断结果
        return if (result.toInt() == 1) key else null
    }

    /**
     * 释放锁
     *
     * @param lockName 锁名称
     * @param key 解锁标识
     */
    fun unlock(lockName: String?, key: String) { // 执行脚本
        redisTemplate.execute(
                UNLOCK_SCRIPT,
                singletonList(lockName),
                key + Thread.currentThread().id, null)
    }
}
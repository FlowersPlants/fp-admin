package com.fpwag.admin.infrastructure.security

import org.springframework.security.core.context.SecurityContextHolder

/**
 * 认证相关工具类
 *
 * @author fpwag
 */
object SecurityUtils {
    /**
     * 获取当前登录信息
     */
    private fun getPrincipal(): Any? {
        return when (val authentication = SecurityContextHolder.getContext().authentication) {
            null -> null
            else -> authentication.principal
        }
    }

    /**
     * 获取当前登录用户（SecurityUser）
     */
    fun getSecurityUser(): SecurityUser? {
        val principal = getPrincipal()
        return principal as? SecurityUser
    }

    /**
     * 获取当前登录用户账号信息
     */
    fun getUsername(): String? {
        return getSecurityUser()?.username
    }

    /**
     * 获取当前登录用户id
     */
    fun getUserId(): String? {
        return getSecurityUser()?.id
    }

    /**
     * 判断当前登录用户是否是超级管理员
     *
     * @return true-是
     */
    fun isAdmin(): Boolean {
        return getSecurityUser()?.admin ?: false
    }
}
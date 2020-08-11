package com.fpwag.admin

import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ApplicationTest {
    private val currentThread: ThreadLocal<Long> = ThreadLocal()

    @Before
    fun before() {
        this.currentThread.set(System.currentTimeMillis())
    }

    @After
    fun after() {
        val time = System.currentTimeMillis() - this.currentThread.get()
        println("执行耗时：$time ms.")
    }
}
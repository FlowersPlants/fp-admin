package com.fpwag.admin.domain.repository

import com.fpwag.admin.ApplicationTest
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class MenuRepositoryTest : ApplicationTest() {
    @Autowired
    private lateinit var repository: MenuRepository

    @Test
    fun selectByUserIdTest() {
        val userId = "admin"
        val list = this.repository.selectByUsername(userId)
        Assert.assertNotNull(list)
    }

    @Test
    fun selectChildrenByIdTest() {
        val id = "1075123423456656412"
        val result = this.repository.selectChildren(id)
        Assert.assertTrue(result.size > 0)
    }
}
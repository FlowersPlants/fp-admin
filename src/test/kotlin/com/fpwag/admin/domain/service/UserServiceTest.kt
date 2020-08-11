package com.fpwag.admin.domain.service

import com.fpwag.admin.ApplicationTest
import org.springframework.beans.factory.annotation.Autowired

class UserServiceTest : ApplicationTest() {
    @Autowired
    private lateinit var service: UserService

//    @Test
//    fun findByIdTest() {
//        val id = "1"
//        val user = this.service.getById(id)
//        Assert.assertTrue(user != null)
//    }
//
//    @Test
//    fun findByUsernameTest() {
//        val username = "admin"
//        val user = this.service.findByUsername(username)
//        Assert.assertTrue(user != null)
//    }
//
//    @Test
//    fun updateStatusTest() {
//        val username = "admin"
//        val lockFlag = "22"
//        this.service.updateStatus(username, lockFlag)
//    }
}
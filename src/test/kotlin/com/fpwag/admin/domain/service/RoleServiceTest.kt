package com.fpwag.admin.domain.service

import com.fpwag.admin.ApplicationTest
import org.springframework.beans.factory.annotation.Autowired

class RoleServiceTest : ApplicationTest() {
    @Autowired
    private lateinit var service: RoleService

//    @Test
//    fun findByIdTest() {
//        val id = "1"
//        val role = this.service.getById(id)
//        Assert.assertTrue(role != null)
//    }
//
//    @Test
//    fun findByUserIdTest() {
//        val userId = "1"
//        val list = this.service.findByUserId(userId)
//        Assert.assertTrue(list.size >= 1)
//    }
//
//    @Test
//    fun findPageTest() {
//        val params = RoleQuery().apply {
//            this.keyword = ""
//        }
//        val pageable = PageRequest.of(0, 10)
//        val result = this.service.findPage(params, MybatisPageMapper.pageableToPage(pageable))
//        Assert.assertTrue(result.total >= 1)
//    }
//
//    @Test
//    fun insertBatchRecordTest() {
//        val dto = RoleAssignCmd().apply {
//            this.id = "r1"
//            this.userIds = mutableListOf("u1", "u2")
//        }
//        val flag = this.service.insertBatchRecord(dto)
//        Assert.assertTrue(flag)
//    }
}
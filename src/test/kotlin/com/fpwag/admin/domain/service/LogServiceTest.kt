package com.fpwag.admin.domain.service

import com.fpwag.admin.ApplicationTest
import org.springframework.beans.factory.annotation.Autowired

class LogServiceTest : ApplicationTest() {
    @Autowired
    private lateinit var service: LogService

//    @Test
//    fun findPageTest() {
//        val params = LogQuery().apply {
//            this.keyword = "G"
//        }
//        val pageable = PageRequest.of(1, 10, Sort.Direction.DESC, "create_time")
//        val result = this.service.findPage(params, MybatisPageMapper.pageableToPage(pageable))
//        Assert.assertTrue(result.total > 5)
//    }
}
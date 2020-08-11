package com.fpwag.admin.domain.service

import com.fpwag.admin.ApplicationTest
import org.springframework.beans.factory.annotation.Autowired

class DictServiceTest : ApplicationTest() {
    @Autowired
    private lateinit var service: DictService

//    @Test
//    fun getDictListTest() {
//        val list = this.service.list()
//        Assert.assertTrue(list != null)
//    }
//
//    @Test
//    fun findPageTest() {
//        val params = DictQuery().apply {
//            this.type = "gender"
//        }
//        val sort = Sort.by(Sort.Order.desc("create_time"), Sort.Order.asc("sort"))
//        val pageable = PageRequest.of(1, 10, sort)
//        val result = this.service.findPage(params, MybatisPageMapper.pageableToPage(pageable))
//        Assert.assertTrue(result.total == 3L)
//    }
//
//    @Test
//    fun saveTest() {
//        val dict = Dict().apply {
//            this.type = "gender"
//            this.label = "保密"
//            this.value = "0"
//            this.sort = 10
//        }
//        val flag = this.service.save(dict)
//        Assert.assertTrue(flag)
//    }
//
//    @Test
//    fun batchSaveTest() {
//        val list = mutableListOf(
//                Dict().apply {
//                    this.type = "gender"
//                    this.label = "男"
//                    this.value = "1"
//                    this.sort = 20
//                },
//                Dict().apply {
//                    this.type = "gender"
//                    this.label = "女"
//                    this.value = "2"
//                    this.sort = 30
//                }
//        )
//        val flag = this.service.saveBatch(list)
//        Assert.assertTrue(flag)
//    }
//
//    @Test
//    fun removeByIdTest() {
//        val id = "1277943648187576321"
//        val flag = this.service.removeById(id)
//        Assert.assertTrue(flag)
//    }
}
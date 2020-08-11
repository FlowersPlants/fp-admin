package com.fpwag.admin.domain.service

import com.fpwag.admin.ApplicationTest
import org.springframework.beans.factory.annotation.Autowired

class MenuServiceTest : ApplicationTest() {
    @Autowired
    private lateinit var service: MenuService

//    @Test
//    fun findByUserIdTest() {
//        val userId = "1"
//        val list = this.service.findByUserId(userId)
//        Assert.assertTrue(list.size > 0)
//    }
//
//    @Test
//    fun findByRoleIdsTest() {
//        val roleIds = arrayListOf("1", "2")
//        val list = this.service.findByRoleIds(roleIds)
//        Assert.assertTrue(list.size > 0)
//    }
//
//    @Test
//    fun findByCodeTest() {
//        val code = "fun_sys_user"
//        val menu = this.service.getOne(QueryWrapper<Menu>().apply {
//            this.eq("code", code)
//        })
//        println(menu.id)
//        Assert.assertTrue(menu != null)
//    }
//
//    @Test
//    fun saveBatchTest() {
//        val menuList = mutableListOf(
//                Menu().apply {
//                    parentId = "1075245354642454543"
//                    code = "fun_sys_user:insert"
//                    permission = "fun_sys_user:insert"
//                    name = "新增"
//                    type = "2" // 按钮
//                    sort = 10
//                    hidden = true
//                },
//                Menu().apply {
//                    parentId = "1075245354642454543"
//                    code = "fun_sys_user:update"
//                    permission = "fun_sys_user:update"
//                    name = "编辑"
//                    type = "2" // 按钮
//                    sort = 20
//                    hidden = true
//                },
//                Menu().apply {
//                    parentId = "1075245354642454543"
//                    code = "fun_sys_user:del"
//                    permission = "fun_sys_user:del"
//                    name = "删除"
//                    type = "2" // 按钮
//                    sort = 30
//                    hidden = true
//                }
//        )
//        val flag = this.service.saveBatch(menuList)
//        Assert.assertTrue(flag)
//    }
//
//    @Test
//    fun removeByCodeAndIdTest() {
//        val id = "222"
//        val code = "fun_ss"
//        val flag = this.service.removeByCodeAndId(id, code)
//        Assert.assertTrue(flag)
//    }
}
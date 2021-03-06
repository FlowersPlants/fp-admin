package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.command.MenuAddCmd
import com.fpwag.admin.domain.dto.input.command.MenuEditCmd
import com.fpwag.admin.domain.dto.input.query.MenuQuery
import com.fpwag.admin.domain.dto.output.MenuDto
import com.fpwag.admin.domain.dto.output.MenuTree
import com.fpwag.admin.domain.service.MenuService
import com.fpwag.admin.domain.service.UserService
import com.fpwag.admin.infrastructure.security.SecurityUtils
import com.fpwag.boot.core.constants.CommonConstants
import com.fpwag.boot.data.mybatis.PageResult
import com.fpwag.boot.logging.annotation.SystemLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@SystemLog(value = "菜单管理")
@RestController
@RequestMapping("/sys/menu")
class MenuController {
    @Autowired
    private lateinit var service: MenuService

    @Autowired
    private lateinit var userService: UserService

    /**
     * 获取当前用户菜单树
     */
    @GetMapping("build")
    fun tree(): MutableList<MenuTree> {
        val username = SecurityUtils.getUsername()
        val user = this.userService.findByUsername(username)
        val menus = this.service.findByUsername(username, user?.admin ?: false)
        return this.service.buildTree(menus)
    }

    @GetMapping("lazy")
    fun lazy(@RequestParam pid: String): MutableList<MenuDto> {
        return this.service.findChildren(pid)
    }

    @GetMapping("{rid}")
    fun findByRole(@PathVariable("rid") rid: String): MutableList<MenuDto> {
        return this.service.findByRole(rid)
    }

    @PreAuthorize("@pms.hasPermission('sys:menu:list')")
    @SystemLog(value = "菜单树查询", type = SystemLog.Type.QUERY)
    @GetMapping
    fun query(query: MenuQuery?): Any {
        val list = this.service.findList(query)
        val records = this.service.buildTree(list, parentId = query?.parentId ?: CommonConstants.ROOT_PARENT_ID)
        return PageResult.of<MenuTree>(records.size, records)
    }

    @PreAuthorize("@pms.hasPermission('sys:menu:add')")
    @SystemLog(value = "新增菜单", type = SystemLog.Type.INSERT)
    @PostMapping
    fun insert(@Validated @RequestBody command: MenuAddCmd): Any? {
        return this.service.save(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:menu:edit')")
    @SystemLog(value = "更新菜单", type = SystemLog.Type.UPDATE)
    @PutMapping
    fun update(@Validated @RequestBody command: MenuEditCmd): Any? {
        return this.service.update(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:menu:del')")
    @SystemLog(value = "批量删除", type = SystemLog.Type.DELETE)
    @DeleteMapping
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }
}

package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.command.MenuAddCmd
import com.fpwag.admin.domain.dto.input.command.MenuEditCmd
import com.fpwag.admin.domain.dto.input.query.MenuQuery
import com.fpwag.admin.domain.dto.output.MenuDto
import com.fpwag.admin.domain.dto.output.MenuTree
import com.fpwag.admin.domain.service.MenuService
import com.fpwag.boot.core.constants.CommonConstants
import com.fpwag.boot.logging.annotation.SystemLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 用户菜单管理接口
 */
@SystemLog(value = "菜单管理")
@Api(tags = ["系统菜单相关接口"])
@RestController
@RequestMapping("/sys/menu")
class MenuController {
    @Autowired
    private lateinit var service: MenuService

    /**
     * 获取当前用户菜单树
     */
    @SystemLog(value = "获取当前用户菜单树", type = SystemLog.Type.QUERY)
    @ApiOperation("获取当前用户菜单树")
    @GetMapping("build")
    fun tree(): MutableList<MenuTree> {
        val menus = this.service.findMenus()
        return this.service.buildTree(menus)
    }

    @SystemLog(value = "获取所有菜单并构建树", type = SystemLog.Type.QUERY)
    @ApiOperation("获取所有菜单并构建树")
    @GetMapping("tree")
    fun getTreeMenu(): MutableList<MenuTree> {
        val list = this.service.findAll()
        return this.service.buildTree(list)
    }

    @SystemLog(value = "返回节点下的全部菜单", type = SystemLog.Type.QUERY)
    @ApiOperation("返回节点下的全部菜单")
    @GetMapping("lazy")
    fun query(@RequestParam pid: String): MutableList<MenuDto> {
        return this.service.findByParentId(pid)
    }

    @SystemLog(value = "菜单树查询", type = SystemLog.Type.QUERY)
    @ApiOperation("菜单树查询")
    @GetMapping
    fun findMenus(query: MenuQuery?): Any {
        val list = this.service.findList(query)
        val records = this.service.buildTree(list, parentId = query?.parentId ?: CommonConstants.ROOT_PARENT_ID)
        return mutableMapOf("records" to records, "total" to records.size)
    }

    @PreAuthorize("@pms.hasPermission('fun:sys:menu:add')")
    @SystemLog(value = "新增菜单", type = SystemLog.Type.INSERT)
    @ApiOperation("菜单管理新增接口")
    @PostMapping
    fun insert(@Validated @RequestBody command: MenuAddCmd): Any? {
        return this.service.save(command)
    }

    @SystemLog(value = "更新菜单", type = SystemLog.Type.UPDATE)
    @ApiOperation("更新菜单")
    @PutMapping
    fun update(@Validated @RequestBody command: MenuEditCmd): Any? {
        return this.service.update(command)
    }

    @SystemLog(value = "批量删除", type = SystemLog.Type.DELETE)
    @ApiOperation("批量删除接口")
    @DeleteMapping
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }
}

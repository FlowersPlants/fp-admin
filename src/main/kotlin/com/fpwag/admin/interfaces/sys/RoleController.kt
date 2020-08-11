package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.command.RoleAddCmd
import com.fpwag.admin.domain.dto.input.command.RoleAuthCmd
import com.fpwag.admin.domain.dto.input.command.RoleEditCmd
import com.fpwag.admin.domain.dto.input.query.RoleQuery
import com.fpwag.admin.domain.service.RoleService
import com.fpwag.boot.logging.annotation.SystemLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 用户角色管理接口
 */
@SystemLog(value = "角色管理")
@Api(tags = ["角色管理相关接口"])
@RestController
@RequestMapping("/sys/role")
class RoleController {
    @Autowired
    private lateinit var service: RoleService

    /**
     * 用户角色管理分页
     */
    @SystemLog(value = "角色分页", type = SystemLog.Type.QUERY)
    @ApiOperation("分页接口")
    @GetMapping
    fun findPage(query: RoleQuery, pageable: Pageable): Any? {
        return this.service.findPage(query, pageable)
    }

    /**
     * 新增接口
     */
    @SystemLog(value = "新增角色", type = SystemLog.Type.INSERT)
    @ApiOperation("新增接口")
    @PostMapping
    fun insert(@Validated @RequestBody command: RoleAddCmd): Any? {
        return this.service.save(command)
    }

    /**
     * 角色授权接口
     */
    @SystemLog(value = "角色授权", type = SystemLog.Type.UPDATE)
    @ApiOperation("角色授权接口")
    @PostMapping("auth")
    fun roleAuth(@Validated @RequestBody command: RoleAuthCmd): Any? {
        return this.service.authMenus(command)
    }

    /**
     * 修改接口
     */
    @SystemLog(value = "角色信息修改", type = SystemLog.Type.UPDATE)
    @ApiOperation("修改接口")
    @PutMapping
    fun update(@Validated @RequestBody command: RoleEditCmd): Any? {
        return this.service.update(command)
    }

    /**
     * 删除接口（逻辑删除）
     */
    @SystemLog(value = "根据id删除角色", type = SystemLog.Type.DELETE)
    @ApiOperation("逻辑删除接口")
    @DeleteMapping
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }
}

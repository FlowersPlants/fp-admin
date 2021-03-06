package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.command.RoleAddCmd
import com.fpwag.admin.domain.dto.input.command.RoleAssignCmd
import com.fpwag.admin.domain.dto.input.command.RoleAuthCmd
import com.fpwag.admin.domain.dto.input.command.RoleEditCmd
import com.fpwag.admin.domain.dto.input.query.RoleQuery
import com.fpwag.admin.domain.dto.output.RoleDto
import com.fpwag.admin.domain.service.RoleService
import com.fpwag.boot.data.mybatis.PageResult
import com.fpwag.boot.logging.annotation.SystemLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 角色管理
 *
 * @author fpwag
 */
@SystemLog(value = "角色管理")
@RestController
@RequestMapping("/sys/role")
class RoleController {
    @Autowired
    private lateinit var service: RoleService

    @PreAuthorize("@pms.hasPermission('sys:role:list')")
    @SystemLog(value = "角色分页", type = SystemLog.Type.QUERY)
    @GetMapping
    fun findPage(query: RoleQuery, pageable: Pageable): PageResult<RoleDto>? {
        return this.service.findPage(query, pageable)
    }

    @PreAuthorize("@pms.hasPermission('sys:role:add')")
    @SystemLog(value = "新增角色", type = SystemLog.Type.INSERT)
    @PostMapping
    fun insert(@Validated @RequestBody command: RoleAddCmd): Any? {
        return this.service.save(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:role:auth')")
    @SystemLog(value = "角色授权", type = SystemLog.Type.UPDATE)
    @PutMapping("auth")
    fun roleAuth(@Validated @RequestBody command: RoleAuthCmd): Any? {
        return this.service.authMenus(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:role:edit')")
    @SystemLog(value = "用户分配", type = SystemLog.Type.UPDATE)
    @PutMapping("assign")
    fun assign(@Validated @RequestBody command: RoleAssignCmd): Any? {
        return this.service.assignUsers(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:role:edit')")
    @SystemLog(value = "修改角色", type = SystemLog.Type.UPDATE)
    @PutMapping
    fun update(@Validated @RequestBody command: RoleEditCmd): Any? {
        return this.service.update(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:role:del')")
    @SystemLog(value = "删除角色", type = SystemLog.Type.DELETE)
    @DeleteMapping
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }
}

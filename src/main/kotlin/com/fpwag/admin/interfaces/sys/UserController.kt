package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.UpdateStatusCmd
import com.fpwag.admin.domain.dto.input.command.UserAddCmd
import com.fpwag.admin.domain.dto.input.command.UserEditCmd
import com.fpwag.admin.domain.dto.input.command.UserResetPwdCmd
import com.fpwag.admin.domain.dto.input.command.UserUpdatePwdCmd
import com.fpwag.admin.domain.dto.input.query.UserQuery
import com.fpwag.admin.domain.dto.output.UserDto
import com.fpwag.admin.domain.service.UserService
import com.fpwag.admin.infrastructure.security.SecurityUtils
import com.fpwag.boot.autoconfigure.oss.StorageService
import com.fpwag.boot.logging.annotation.SystemLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * 用户管理
 *
 * @author FlowersPlants
 * @since v1
 **/
@SystemLog(value = "用户管理")
@RestController
@RequestMapping("/sys/user")
class UserController {
    @Autowired
    private lateinit var service: UserService

    @Autowired
    private lateinit var storageService: StorageService

    @GetMapping("info")
    fun getInfo(): Any {
        val username = SecurityUtils.getUsername()
        val info = this.service.findByUsername(username)
        return mutableMapOf(
                "info" to info,
                "authorities" to this.service.getAuthorities(username, info?.admin ?: false)
        )
    }

    @GetMapping("{rid}")
    fun findByRole(@PathVariable("rid") rid: String): MutableList<UserDto> {
        return this.service.findByRole(rid)
    }

    @PreAuthorize("@pms.hasPermission('sys:user:list')")
    @SystemLog(value = "用户分页", type = SystemLog.Type.QUERY)
    @GetMapping
    fun findPage(user: UserQuery?, pageable: Pageable): Any? {
        return this.service.findPage(user, pageable)
    }

    @PreAuthorize("@pms.hasPermission('sys:user:add')")
    @SystemLog(value = "新增用户", type = SystemLog.Type.INSERT)
    @PostMapping
    fun insert(@Validated @RequestBody command: UserAddCmd): Any? {
        return this.service.save(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:user:edit')")
    @SystemLog(value = "修改用户", type = SystemLog.Type.UPDATE)
    @PutMapping
    fun update(@Validated @RequestBody command: UserEditCmd): Any? {
        return this.service.update(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:user:del')")
    @SystemLog(value = "删除用户", type = SystemLog.Type.DELETE)
    @DeleteMapping
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }

    @PreAuthorize("@pms.hasPermission('sys:user:repwd')")
    @SystemLog(value = "重置密码", type = SystemLog.Type.UPDATE)
    @PutMapping("repwd")
    fun resetPwd(@Validated @RequestBody command: UserResetPwdCmd): String {
        return this.service.resetPwd(command)
    }

    @SystemLog(value = "修改状态", type = SystemLog.Type.UPDATE)
    @PutMapping(value = ["us"])
    fun updateStatus(@Validated @RequestBody command: UpdateStatusCmd): Any? {
        return this.service.updateStatus(command)
    }

    // 以下为个人信息修改接口
    @SystemLog(value = "修改密码", type = SystemLog.Type.UPDATE)
    @PutMapping("up")
    fun updatePwd(@Validated @RequestBody command: UserUpdatePwdCmd): Any? {
        return this.service.updatePwd(command)
    }

    @SystemLog(value = "修改头像", type = SystemLog.Type.UPDATE)
    @PostMapping(value = ["ua"])
    fun updateAvatar(@RequestBody @RequestParam("avatar") avatar: MultipartFile): Any? {
        val meta = this.storageService.upload(avatar)
        return this.service.updateAvatar(meta.accessUrl)
    }
}

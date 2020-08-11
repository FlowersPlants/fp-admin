package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.application.service.SystemService
import com.fpwag.admin.infrastructure.security.token.TokenInfo
import com.fpwag.admin.domain.dto.input.UserCommand
import com.fpwag.admin.domain.dto.input.command.UserAddCmd
import com.fpwag.admin.domain.dto.input.command.UserEditCmd
import com.fpwag.admin.domain.dto.input.command.UserUpdatePwdCmd
import com.fpwag.admin.domain.dto.input.query.UserQuery
import com.fpwag.admin.domain.service.UserService
import com.fpwag.admin.infrastructure.security.SecurityUtils
import com.fpwag.boot.logging.annotation.SystemLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 用户管理相关接口
 * @author FlowersPlants
 * @since v1
 **/
@SystemLog(value = "用户管理")
@Api(tags = ["用户相关接口"])
@RestController
@RequestMapping("/sys/user")
class UserController {
    @Autowired
    private lateinit var service: UserService

    @Autowired
    protected lateinit var systemService: SystemService

    /**
     * 获取当前登录用户基本信息
     */
    @SystemLog(value = "获取当前用户信息", type = SystemLog.Type.QUERY)
    @ApiOperation("获取当前用户信息")
    @GetMapping("info")
    fun getInfo(): TokenInfo {
        val username = SecurityUtils.getUsername()
        return this.systemService.getUserInfo(username)
    }

    /**
     * 分页接口
     */
    @SystemLog(value = "用户分页", type = SystemLog.Type.QUERY)
    @ApiOperation("用户管理分页接口")
    @GetMapping
    @PreAuthorize("@pms.hasPermission('sys:user:list')")
    fun findPage(user: UserQuery?, pageable: Pageable): Any? {
        return this.service.findPage(user, pageable)
    }

    /**
     * 新增接口
     */
    @SystemLog(value = "新增用户", type = SystemLog.Type.INSERT)
    @ApiOperation("新增接口")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys:user:add')")
    fun insert(@Validated @RequestBody command: UserAddCmd): Any? {
        return this.service.save(command)
    }

    /**
     * 修改接口
     */
    @SystemLog(value = "修改用户信息", type = SystemLog.Type.UPDATE)
    @ApiOperation("修改接口")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys:user:edit')")
    fun update(@Validated @RequestBody command: UserEditCmd): Any? {
        return this.service.update(command)
    }

    /**
     * 删除接口（逻辑删除）
     */
    @SystemLog(value = "批量删除用户", type = SystemLog.Type.DELETE)
    @ApiOperation("逻辑删除接口")
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('sys:user:del')")
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }

    // 以下为个人信息修改接口
    @SystemLog(value = "重置密码", type = SystemLog.Type.UPDATE)
    @ApiOperation("重置密码")
    @ApiImplicitParam(name = "random", value = "密码是否随机生成", required = false)
    @PutMapping(value = ["{username}/reset-pwd"])
    fun resetPwd(@PathVariable("username") username: String, random: Boolean = false): String {
        return this.systemService.resetPwd(username, random)
    }

    @SystemLog(value = "修改密码", type = SystemLog.Type.UPDATE)
    @ApiOperation("修改密码")
    @ApiImplicitParams(value = [
        ApiImplicitParam(name = "oldEncryptPwd", value = "旧密码（md5加密）", required = true),
        ApiImplicitParam(name = "encryptPwd", value = "新密码（md5加密）", required = true)
    ])
    @PutMapping(value = ["{userId}/update-pwd"])
    @Deprecated("暂时去掉", ReplaceWith("this.updatePwd(username, command)"))
    fun updatePwd(@PathVariable("userId") userId: String, oldEncryptPwd: String, encryptPwd: String): Any? {
        return this.systemService.updatePwd(userId, oldEncryptPwd, encryptPwd)
    }

    @SystemLog(value = "修改状态", type = SystemLog.Type.UPDATE)
    @ApiOperation("修改状态")
    @ApiImplicitParam(name = "lockFlag", value = "用户状态", required = true)
    @PutMapping(value = ["{username}/us"])
    fun updateStatus(@PathVariable("username") username: String, lockFlag: String): Any? {
        return this.service.updateInfo(UserCommand(username, UserCommand.Type.FLAG, lockFlag))
    }

    @PutMapping("{username}/up")
    fun updatePwd(@PathVariable("username")username: String, @RequestBody command: UserUpdatePwdCmd): Any? {
        return null
    }

    @SystemLog(value = "修改邮箱", type = SystemLog.Type.UPDATE)
    @ApiOperation("修改邮箱")
    @ApiImplicitParam(name = "email", value = "用户邮箱", required = true)
    @PutMapping(value = ["{username}/ue"])
    fun updateEmail(@PathVariable("username") username: String, email: String): Any? {
        return this.service.updateInfo(UserCommand(username, UserCommand.Type.EMAIL, email))
    }

    @SystemLog(value = "修改头像", type = SystemLog.Type.UPDATE)
    @ApiOperation("修改头像")
    @ApiImplicitParam(name = "avatar", value = "用户头像地址", required = true)
    @PutMapping(value = ["{username}/ua"])
    fun updateAvatar(@PathVariable("username") username: String, avatar: String): Any? {
        return this.service.updateInfo(UserCommand(username, UserCommand.Type.AVATAR, avatar))
    }
}

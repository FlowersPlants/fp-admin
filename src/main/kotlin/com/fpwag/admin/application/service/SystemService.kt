package com.fpwag.admin.application.service

import cn.hutool.core.util.RandomUtil
import com.fpwag.admin.infrastructure.security.token.UserInfo
import com.fpwag.admin.infrastructure.security.token.TokenInfo
import com.fpwag.admin.domain.dto.input.UserCommand
import com.fpwag.admin.domain.service.MenuService
import com.fpwag.admin.domain.service.RoleService
import com.fpwag.admin.domain.service.UserService
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.core.constants.CommonConstants
import com.fpwag.boot.core.exception.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.util.DigestUtils

/**
 * 系统模块业务处理，
 * 不直接使用repository是为了在service中使用缓存，
 * 当然也可以使用repository和缓存操作类{@see RedisOperator}实现
 *
 * @author fpwag
 */
@Component
class SystemService {
    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var roleService: RoleService

    @Autowired
    private lateinit var menuService: MenuService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    /**
     * 根据账号获取用户信息
     *
     * @param account 用户账号
     * @param protectedPwd 用户的密码是否受保护的，如果是则把密码替换成"N/A"
     * @return 用户信息，包括角色和菜单权限
     */
    fun getUserInfo(account: String?, protectedPwd: Boolean = true): TokenInfo {
        val user = this.userService.findByUsername(account)
        Assert.isTrue(user != null, "获取用户{}信息失败", account)

        val roles = this.roleService.findByUserId(user!!.id).mapNotNull { it.code }
        val permissions = this.menuService.findByUserId(user.id).mapNotNull { it.permission }

        val authorities = mutableListOf(*roles.toTypedArray(), *permissions.toTypedArray())

        val password = if (protectedPwd) CommonConstant.PROTECTED_PWD else user.password!!
        val info = UserInfo(user.id, user.deptId!!, user.username!!, password).apply {
            this.avatar = user.avatar
            this.email = user.email
            this.mobile = user.mobile
            this.lockFlag = user.lockFlag
            // TODO this.admin =
            this.admin = true
        }
        return TokenInfo(info, authorities)
    }

    /**
     * 修改密码
     *
     * @param userId 用户账号
     * @param oldEncryptPwd 旧密码（md5加密）
     * @param encryptPwd 新密码（md5加密）
     */
    fun updatePwd(userId: String, oldEncryptPwd: String, encryptPwd: String) {
        val user = this.userService.findById(userId)
        val flag = this.passwordEncoder.matches(oldEncryptPwd, user?.password)
        Assert.isTrue(flag, "旧密码错误，修改密码失败")

        val pwd = this.passwordEncoder.encode(encryptPwd)
        this.userService.updateInfo(UserCommand(userId, UserCommand.Type.PWD, pwd))
    }

    /**
     * <p>
     * 重置密码，直接重置为系统默认的密码<br>
     * 也可以生成一个随机密码
     *
     * @param userId 用户id
     */
    fun resetPwd(userId: String, random: Boolean = false): String {
        var encryptPassword = CommonConstants.DEFAULT_USER_PWD
        if (random) {
            val password = RandomUtil.randomString(12)
            encryptPassword = DigestUtils.md5DigestAsHex(password.toByteArray())
        }

        val encodedPassword = this.passwordEncoder.encode(encryptPassword)
        this.userService.updateInfo(UserCommand(userId, UserCommand.Type.PWD, encodedPassword))

        return encryptPassword
    }
}
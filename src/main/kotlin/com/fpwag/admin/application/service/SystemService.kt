package com.fpwag.admin.application.service

import cn.hutool.core.util.RandomUtil
import com.fpwag.admin.domain.dto.input.UserCommand
import com.fpwag.admin.domain.service.MenuService
import com.fpwag.admin.domain.service.RoleService
import com.fpwag.admin.domain.service.UserService
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
     * 获取某个用户的所有权限和角色集合
     *
     * @param username 用户名
     * @param admin 是否超级管理员，默认false
     * @return 权限列表
     */
    fun getAuthorities(username: String?, admin: Boolean = false): Collection<String> {
        val roles = this.roleService.findByUsername(username).mapNotNull { it.code }
        val permissions = this.menuService.findByUsername(username, admin).mapNotNull { it.permission }
        return mutableListOf(*roles.toTypedArray(), *permissions.toTypedArray())
    }

    /**
     * 修改密码
     * TODO 移到事件监听处理器中
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
     * TODO 移到事件监听处理器中
     *
     * @param userId 用户id
     */
    fun resetPwd(userId: String, random: Boolean = false): String {
        var defaultPwd = "fpwag1234!"
        var encryptPassword = CommonConstants.DEFAULT_USER_PWD
        if (random) {
            defaultPwd = RandomUtil.randomString(12)
            encryptPassword = DigestUtils.md5DigestAsHex(defaultPwd.toByteArray())
        }

        val encodedPassword = this.passwordEncoder.encode(encryptPassword)
        this.userService.updateInfo(UserCommand(userId, UserCommand.Type.PWD, encodedPassword))

        return defaultPwd
    }
}
package com.fpwag.admin.application.service

import com.fpwag.admin.infrastructure.security.SecurityUser
import com.fpwag.boot.core.exception.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * 自定义details service对象
 * @author FlowersPlants
 * @since v1
 */
@Service
class SecurityUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var systemService: SystemService

    /**
     * 根据用户名获取用户详情
     * @param username 用户账号
     * @return 用户信息,先从缓存获取
     */
    override fun loadUserByUsername(username: String?): UserDetails {
        Assert.isTrue(!username.isNullOrBlank(), "用户名或密码错误")
        val user = this.systemService.getUserInfo(account = username, protectedPwd = false)
        return run {
            val authorities = AuthorityUtils.createAuthorityList(*user.authorities.toTypedArray())
            val info = user.userInfo
            Assert.isTrue(info.lockFlag != true, "用户已禁用，请联系管理员")

            SecurityUser(info.id, info.roleId, info.username, info.password, authorities)
        }
    }
}

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

        val info = this.systemService.getUserInfo(username)
        val user = info.info

        Assert.isTrue(user.status == true, "用户已禁用，请联系管理员")

        val authorities = AuthorityUtils.createAuthorityList(*info.authorities.toTypedArray())

        return SecurityUser(user.id!!, user.username!!, user.password!!, authorities)
    }
}

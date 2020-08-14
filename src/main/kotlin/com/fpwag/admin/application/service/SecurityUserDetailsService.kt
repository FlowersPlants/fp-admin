package com.fpwag.admin.application.service

import com.fpwag.admin.domain.service.UserService
import com.fpwag.boot.core.exception.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

/**
 * 自定义details service对象
 *
 * @author FlowersPlants
 * @since v1
 */
@Service
class SecurityUserDetailsService : UserDetailsService {
    @Autowired
    private lateinit var userService: UserService
    @Autowired
    private lateinit var systemService: SystemService

    /**
     * 根据用户名获取用户详情
     *
     * @param username 用户账号
     * @return 用户信息,先从缓存获取
     */
    override fun loadUserByUsername(username: String?): UserDetails {
        Assert.isTrue(!username.isNullOrBlank(), "用户名或密码错误")

        val user = this.userService.findByUsername(username)

        Assert.isTrue(user != null, "用户名或密码错误")
        Assert.isTrue(user!!.status == true, "用户已禁用，请联系管理员")

        val authorityList = this.systemService.getAuthorities(username, user.admin)
        val authorities = AuthorityUtils.createAuthorityList(*authorityList.toTypedArray())

        return User(user.username!!, user.password!!, authorities)
    }
}

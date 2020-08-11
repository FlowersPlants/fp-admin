package com.fpwag.admin.infrastructure.security

import com.fpwag.admin.infrastructure.CommonConstant
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

/**
 * 系统安全认证用户
 * @author FlowersPlants
 * @since v1
 */
class SecurityUser : User {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var id: String

    /**
     * 部门id
     */
    var deptId: String

    /**
     * 当前登录用户是否是超级管理员（自动拥有所有权限）
     */
    var admin: Boolean = false

    constructor(id: String, deptId: String, admin: Boolean, username: String, password: String, authorities: List<GrantedAuthority>) : super(username, password, authorities) {
        this.id = id
        this.deptId = deptId
        this.admin = admin
    }

    constructor(id: String, deptId: String, admin: Boolean, username: String, password: String, enabled: Boolean, accountNonExpired: Boolean, credentialsNonExpired: Boolean, accountNonLocked: Boolean, authorities: List<GrantedAuthority>)
            : super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) {
        this.id = id
        this.deptId = deptId
        this.admin = admin
    }
}
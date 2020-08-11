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

    var roleId: String

    constructor(id: String, roleId: String, username: String, password: String, authorities: List<GrantedAuthority>) : super(username, password, authorities) {
        this.id = id
        this.roleId = roleId
    }

    constructor(id: String, roleId: String, username: String, password: String, enabled: Boolean, accountNonExpired: Boolean, credentialsNonExpired: Boolean, accountNonLocked: Boolean, authorities: List<GrantedAuthority>)
            : super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) {
        this.id = id
        this.roleId = roleId
    }
}
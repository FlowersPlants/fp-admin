package com.fpwag.admin.infrastructure.security

import com.fpwag.admin.infrastructure.CommonConstant
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

/**
 * 系统安全认证用户
 * @author FlowersPlants
 * @since v1
 */
@Deprecated(message = "暂时没用")
class SecurityUser : User {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var id: String

    /**
     * 部门id
     */
    var deptId: String? = null

    constructor(id: String, username: String, password: String, authorities: Collection<GrantedAuthority>)
            : super(username, password, authorities) {
        this.id = id
    }

    constructor(id: String, username: String, password: String, enabled: Boolean, accountNonExpired: Boolean, credentialsNonExpired: Boolean, accountNonLocked: Boolean, authorities: Collection<GrantedAuthority>)
            : super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) {
        this.id = id
    }
}
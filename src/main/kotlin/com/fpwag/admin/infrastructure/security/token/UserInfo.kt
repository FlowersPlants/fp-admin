package com.fpwag.admin.infrastructure.security.token

/**
 * 用户信息
 *
 * @author fpwag
 */
data class UserInfo(var id: String, var roleId: String, var username: String, var password: String) {
    var avatar: String? = null
    var email: String? = null
    var mobile: String? = null
    var lockFlag: Boolean? = null
}
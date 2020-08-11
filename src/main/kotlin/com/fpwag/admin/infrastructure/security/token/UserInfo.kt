package com.fpwag.admin.infrastructure.security.token

/**
 * 用户信息
 *
 * @author fpwag
 */
data class UserInfo(var id: String, var deptId: String, var username: String, var password: String) {
    var avatar: String? = null
    var email: String? = null
    var mobile: String? = null
    var lockFlag: Boolean? = null
    var admin: Boolean = false // 当前用户是否是超级管理员
}
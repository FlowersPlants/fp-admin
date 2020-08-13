package com.fpwag.admin.infrastructure

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable

/**
 * 用户信息定义
 *
 * @author fpwag
 */
class UserInfo : Serializable {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var id: String? = null
    var deptId: String? = null
    var username: String? = null

    @JsonIgnore
    var password: String? = null

    var avatar: String? = null
    var email: String? = null
    var mobile: String? = null
    var status: Boolean? = null
    var gender: Int? = null

    @JsonIgnore
    var admin: Boolean? = null // 当前用户是否是超级管理员
}
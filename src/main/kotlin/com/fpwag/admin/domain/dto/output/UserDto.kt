package com.fpwag.admin.domain.dto.output

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.support.dto.DataDTO

/**
 * 用户信息输出，不包含密码信息
 *
 * @author fpwag
 */
open class UserDto : DataDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var deptId: String? = null
    var username: String? = null
    var name: String? = null
    var avatar: String? = null
    var email: String? = null
    var mobile: String? = null
    var gender: Int? = null

    @JsonIgnore
    var password: String? = null
    @JsonIgnore
    var admin: Boolean = false
}
package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.data.mybatis.dto.BaseDTO
import java.time.LocalDateTime

/**
 * 用户信息输出，不包含密码信息
 *
 * @author fpwag
 */
open class UserDto : BaseDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var deptId: String? = null
    var username: String? = null
    var name: String? = null
    var avatar: String? = null
    var sort: Int? = null
    var email: String? = null
    var mobile: String? = null
    var gender: String? = null
    var lockFlag: Boolean? = null
    var updateBy: String? = null
    var updateTime: LocalDateTime? = null
}
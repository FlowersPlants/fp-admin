package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.support.dto.DataDTO

/**
 * 角色信息输出
 *
 * @author fpwag
 */
open class RoleDto : DataDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var name: String? = null
    var code: String? = null
    var level: Int? = null
}
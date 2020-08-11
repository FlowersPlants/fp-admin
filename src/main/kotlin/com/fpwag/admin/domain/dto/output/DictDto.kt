package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.data.mybatis.dto.BaseDTO
import java.time.LocalDateTime

/**
 * 字典信息
 *
 * @author fpwag
 */
open class DictDto : BaseDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var name: String? = null
    var code: String? = null
    var sort: Int? = null
    var updateBy: String? = null
    var updateTime: LocalDateTime? = null
}
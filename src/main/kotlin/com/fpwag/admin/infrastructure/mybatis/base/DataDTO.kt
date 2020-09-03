package com.fpwag.admin.infrastructure.mybatis.base

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.base.BaseDTO
import java.time.LocalDateTime

/**
 * 所有必须返回的公共字段
 *
 * @author fpwag
 */
abstract class DataDTO : BaseDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var remarks: String? = null
    var status: Boolean? = null
    var sort: Int? = null
    var updateTime: LocalDateTime? = null
    var updateBy: String? = null
}
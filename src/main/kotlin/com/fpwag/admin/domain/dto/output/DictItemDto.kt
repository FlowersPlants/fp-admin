package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.data.mybatis.dto.BaseDTO
import java.time.LocalDateTime

/**
 * 字典详情
 *
 * @author fpwag
 */
open class DictItemDto : BaseDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    /**
     * 字典分类id
     */
    var dictId: String? = null

    var label: String? = null
    var value: String? = null
    var sort: Int? = null
    var updateBy: String? = null
    var updateTime: LocalDateTime? = null
}
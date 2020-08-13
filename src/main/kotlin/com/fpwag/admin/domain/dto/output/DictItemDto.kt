package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.support.dto.DataDTO

/**
 * 字典详情
 *
 * @author fpwag
 */
open class DictItemDto : DataDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    /**
     * 字典分类id
     */
    var dictId: String? = null

    var label: String? = null
    var value: String? = null
}
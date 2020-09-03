package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.base.DataDTO

/**
 * 字典信息
 *
 * @author fpwag
 */
open class DictDto : DataDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var name: String? = null
    var code: String? = null
}
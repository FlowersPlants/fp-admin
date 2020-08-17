package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.support.dto.DataDTO

/**
 * 菜单数据传输对象
 *
 * @author fpwag
 */
open class MenuDto : DataDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var parentId: String? = null
    var permission: String? = null
    var type: String? = null
    var name: String? = null
    var path: String? = null
    var component: String? = null
    var icon: String? = null
    var hidden: Boolean? = null
    var cache: Boolean? = null
}
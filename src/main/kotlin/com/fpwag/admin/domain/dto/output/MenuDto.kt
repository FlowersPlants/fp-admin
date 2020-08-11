package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.data.mybatis.dto.BaseDTO
import java.time.LocalDateTime

/**
 * 菜单数据传输对象
 *
 * @author fpwag
 */
open class MenuDto : BaseDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var parentId: String? = null
    var permission: String? = null
    var code: String? = null
    var type: String? = null
    var name: String? = null
    var path: String? = null
    var component: String? = null
    var componentName: String? = null
    var sort: Int? = null
    var icon: String? = null
    var hidden: Boolean? = null
    var cache: Boolean? = null
    var updateBy: String? = null
    var updateTime: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MenuDto) return false

        if (id != other.id) return false
        if (parentId != other.parentId) return false
        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (parentId?.hashCode() ?: 0)
        result = 31 * result + (code?.hashCode() ?: 0)
        return result
    }
}
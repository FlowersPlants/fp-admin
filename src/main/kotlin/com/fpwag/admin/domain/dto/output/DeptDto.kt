package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.domain.entity.Dept
import com.fpwag.boot.data.mybatis.dto.BaseDTO
import java.time.LocalDateTime

open class DeptDto : BaseDTO() {
    var parent: DeptDto? = null
    var roles: MutableSet<RoleDto>? = null

    var name: String? = null
    var sort: Int? = null
    var updateBy: String? = null
    var updateTime: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Dept) return false

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }
}
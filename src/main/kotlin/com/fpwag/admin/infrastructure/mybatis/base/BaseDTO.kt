package com.fpwag.admin.infrastructure.mybatis.base

import com.fasterxml.jackson.annotation.JsonInclude
import com.fpwag.admin.infrastructure.CommonConstant
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 一个包含id的dto对象，返回给前端的所有实体信息需要继承此类进行包装
 *
 * @author fpwag
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
abstract class BaseDTO : Serializable {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var id: String? = null
    var createBy: String? = null
    var createTime: LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseDTO) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "BaseDTO(id=$id)"
    }
}
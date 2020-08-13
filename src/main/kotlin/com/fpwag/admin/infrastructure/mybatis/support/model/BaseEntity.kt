package com.fpwag.admin.infrastructure.mybatis.support.model

import com.baomidou.mybatisplus.annotation.*
import com.fpwag.admin.infrastructure.CommonConstant
import java.io.Serializable
import java.time.LocalDateTime

/**
 * 基于mybatis-plus定义的实体基类，包含一些必要的字段信息
 *
 * @author fpwag
 */
abstract class BaseEntity : Serializable {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    open var id: String? = null

    @TableField(fill = FieldFill.INSERT)
    var createTime: LocalDateTime? = null

    @TableField(fill = FieldFill.INSERT)
    var createBy: String? = null

    @TableLogic(value = "1", delval = "0")
    var enabled: Boolean? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseEntity) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "BaseEntity(id=$id)"
    }
}
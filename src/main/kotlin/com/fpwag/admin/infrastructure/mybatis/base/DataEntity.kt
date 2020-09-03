package com.fpwag.admin.infrastructure.mybatis.base

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.base.BaseEntity
import java.time.LocalDateTime

/**
 * 包含此业务中必须的字段信息
 *
 * @author fpwag
 */
abstract class DataEntity : BaseEntity() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    /**
     * 状态，1-启用，0-禁用
     */
    var status: Boolean? = null

    /**
     * 排序号
     */
    var sort: Int? = null

    var remarks: String? = null

    @TableField(fill = FieldFill.UPDATE)
    var updateTime: LocalDateTime? = null

    @TableField(fill = FieldFill.UPDATE)
    var updateBy: String? = null
}
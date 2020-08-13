package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.support.model.DataEntity

/**
 * 字典实体
 */
@TableName("sys_dict")
class Dict() : DataEntity() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    constructor(id: String?) : this() {
        this.id = id
    }

    /**
     * 字典名称
     */
    var name: String? = null

    var code: String? = null
}

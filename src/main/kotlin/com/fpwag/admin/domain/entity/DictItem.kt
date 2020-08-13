package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.support.model.DataEntity

@TableName("sys_dict_item")
class DictItem() : DataEntity() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    constructor(id: String?) : this() {
        this.id = id
    }

    /**
     * 数据值
     */
    var value: String? = null

    /**
     * 标签名
     */
    var label: String? = null

    /**
     * 字典类型id
     */
    var dictId: String? = null
}
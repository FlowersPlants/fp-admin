package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.data.mybatis.model.WholeModel

@TableName("sys_dict_item")
class DictItem : WholeModel() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
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

    /**
     * 排序号
     */
    var sort: Int? = null
}
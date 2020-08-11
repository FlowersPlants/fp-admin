package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.data.mybatis.model.WholeModel

/**
 * 字典实体
 */
@TableName("sys_dict")
class Dict : WholeModel() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    /**
     * 字典名称
     */
    var name: String? = null

    var code: String? = null

    var sort: Int? = null
}

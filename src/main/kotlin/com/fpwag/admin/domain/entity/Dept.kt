package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.base.DataEntity

/**
 * 部门信息
 *
 * @author fpwag
 */
@TableName(value = "sys_dept")
class Dept() : DataEntity() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    constructor(id: String?) : this() {
        this.id = id
    }

    var parentId: String? = null
    var name: String? = null
    var code: String? = null
}
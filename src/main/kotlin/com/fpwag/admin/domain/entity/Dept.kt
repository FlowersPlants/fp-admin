package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.data.mybatis.model.WholeModel

/**
 * 部门信息
 *
 * @author fpwag
 */
@TableName(value = "sys_dept")
class Dept : WholeModel() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var parentId: String? = null
    var name: String? = null
    var sort: Int? = null
}
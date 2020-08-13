package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.core.TreeNode
import java.time.LocalDateTime

class DeptTree : TreeNode() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var name: String? = null
    var status: Boolean? = null
    var createTime: LocalDateTime? = null
    var createBy: String? = null
}
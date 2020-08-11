package com.fpwag.admin.domain.dto.input.command

import com.fpwag.boot.core.cqrs.Command
import javax.validation.constraints.NotBlank

class DeptAddCmd : Command() {
    @NotBlank(message = "上级部门不能为空")
    var parentId: String? = null

    @NotBlank(message = "部门名称不能为空")
    var name: String? = null

    var sort: Int? = null
    var remarks: String? = null
}
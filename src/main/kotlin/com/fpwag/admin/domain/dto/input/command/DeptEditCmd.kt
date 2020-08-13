package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank

class DeptEditCmd {
    @NotBlank(message = "字典id不能为空")
    var id: String? = null

    var parentId: String? = null
    var name: String? = null
    var sort: Int? = null
    var remarks: String? = null
}
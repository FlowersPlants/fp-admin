package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank

/**
 * 新增用户请求参数，用户重要信息重新提供接口
 *
 * @author fpwag
 */
class UserAddCmd {
    @NotBlank(message = "所属部门不能为空")
    var deptId: String? = null

    @NotBlank(message = "用户名不能为空")
    var username: String? = null

    var name: String? = null

    var sort: Int? = null

    var remarks: String? = null
}
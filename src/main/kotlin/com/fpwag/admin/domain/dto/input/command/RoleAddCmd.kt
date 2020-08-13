package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank

/**
 * 新增角色请求参数
 *
 * @see com.fpwag.admin.domain.entity.Role
 * @author fpwag
 */
class RoleAddCmd {
    @NotBlank(message = "角色名称不能为空")
    var name: String? = null

    @NotBlank(message = "角色编码不能为空")
    var code: String? = null

    var level: Int? = null

    var sort: Int? = null

    var remarks: String? = null
}
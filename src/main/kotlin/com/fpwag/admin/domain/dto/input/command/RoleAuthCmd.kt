package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * 角色授权请求参数
 *
 * @author fpwag
 */
class RoleAuthCmd {
    /**
     * 角色ID
     */
    @NotBlank(message = "角色id不能为空")
    var id: String? = null

    /**
     * 菜单ID集合
     */
    @NotNull(message = "授权菜单不能为空")
    var menuIds: MutableList<String>? = null
}
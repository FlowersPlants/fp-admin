package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank

/**
 * 新增菜单请求参数
 *
 * @see com.fpwag.admin.domain.entity.Menu
 * @author fpwag
 */
class MenuAddCmd {
    @NotBlank(message = "父ID不能为空")
    var parentId: String? = null

    @NotBlank(message = "菜单名称不能为空")
    var name: String? = null

    @NotBlank(message = "菜单编码不能为空")
    var code: String? = null

    var permission: String? = null

    @NotBlank(message = "菜单类型不能为空")
    var type: String? = null

    var path: String? = null

    var component: String? = null

    var icon: String? = null

    var hidden: Boolean? = null

    var cache: Boolean? = null

    var sort: Int? = null

    var remarks: String? = null
}
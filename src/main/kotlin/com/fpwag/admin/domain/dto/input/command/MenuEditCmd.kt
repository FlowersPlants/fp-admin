package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank

/**
 * 修改菜单请求参数
 *
 * @see com.fpwag.admin.domain.entity.Menu
 * @author fpwag
 */
class MenuEditCmd {
    @NotBlank(message = "字典id不能为空")
    var id: String? = null

    var parentId: String? = null

    var name: String? = null

    var permission: String? = null

    var type: String? = null

    var path: String? = null

    var component: String? = null

    var icon: String? = null

    var hidden: Boolean? = null

    var cache: Boolean? = null

    var sort: Int? = null

    var remarks: String? = null
}
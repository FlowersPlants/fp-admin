package com.fpwag.admin.domain.dto.input.command

import com.fpwag.boot.core.cqrs.Command
import javax.validation.constraints.NotBlank

/**
 * 修改用户请求参数，不能修改账号重要信息
 *
 * @author fpwag
 */
class UserEditCmd : Command() {
    @NotBlank(message = "字典id不能为空")
    var id: String? = null

    var roleId: String? = null

    var name: String? = null

    var sort: Int? = null

    var remarks: String? = null
}
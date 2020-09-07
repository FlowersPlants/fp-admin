package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank

class UserUpdatePwdCmd {
    @NotBlank(message = "旧密码不能为空")
    var oldPass: String? = null

    @NotBlank(message = "新密码不能为空")
    var newPass: String? = null
}
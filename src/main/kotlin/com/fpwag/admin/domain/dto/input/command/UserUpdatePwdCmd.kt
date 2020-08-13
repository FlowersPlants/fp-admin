package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class UserUpdatePwdCmd {
    @NotBlank(message = "用户账号不能为空")
    var username: String? = null

    @NotNull(message = "状态不能为null")
    var oldEncryptPwd: String? = null

    var encryptPwd: String? = null

    var confirmEncryptPwd: String? = null
}
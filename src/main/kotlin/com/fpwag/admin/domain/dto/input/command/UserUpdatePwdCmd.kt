package com.fpwag.admin.domain.dto.input.command

import com.fpwag.boot.core.cqrs.Command
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class UserUpdatePwdCmd : Command() {
    @NotBlank(message = "用户账号不能为空")
    var username: String? = null

    @NotNull(message = "状态不能为null")
    var oldEncryptPwd: String? = null

    var encryptPwd: String? = null

    var confirmEncryptPwd: String? = null
}
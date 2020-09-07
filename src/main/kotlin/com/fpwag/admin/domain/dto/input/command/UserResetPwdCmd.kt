package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotNull

class UserResetPwdCmd {
    @NotNull(message = "待重置用户")
    var ids: MutableSet<String> = mutableSetOf()
    var random: Boolean = false
}
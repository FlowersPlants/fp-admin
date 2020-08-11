package com.fpwag.admin.domain.dto.input

import javax.validation.constraints.NotBlank

/**
 * 登录请求参数
 *
 * @author fwpag
 */
class LoginUser {
    @NotBlank(message = "用户名不能为空")
    var username: String? = null

    @NotBlank(message = "用户密码不能为空")
    var password: String? = null

    var code: String? = null
    var uuid: String? = null
    var rememberMe: Boolean = false
}
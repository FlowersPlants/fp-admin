package com.fpwag.admin.interfaces.auth

import com.fpwag.admin.application.service.AuthService
import com.fpwag.admin.domain.dto.input.LoginUser
import com.fpwag.boot.logging.annotation.SystemLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * 权限认证
 * @author FlowersPlants
 * @since v1
 */
@RestController
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private lateinit var authService: AuthService

    @GetMapping("code")
    fun code(): Any {
        return this.authService.getCode()
    }

    @SystemLog(value = "登录", type = SystemLog.Type.LOGIN, saveResult = false)
    @PostMapping("login")
    fun login(@Validated @RequestBody logUser: LoginUser): String {
        return this.authService.login(logUser)
    }

    @SystemLog(value = "登出", type = SystemLog.Type.LOGOUT)
    @DeleteMapping("logout")
    fun logout(): Boolean {
        return true
    }
}

package com.fpwag.admin.interfaces.auth

import com.fpwag.admin.application.service.AuthService
import com.fpwag.admin.domain.dto.output.ImageCodeVO
import com.fpwag.admin.domain.dto.input.LoginUser
import com.fpwag.boot.logging.annotation.SystemLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
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
@Api(tags = ["认证相关接口"])
class AuthController {
    @Autowired
    private lateinit var authService: AuthService

    /**
     * 获取图形验证码
     */
    @ApiOperation("获取图形验证码")
    @GetMapping("code")
    fun code(): ImageCodeVO {
        return this.authService.getCode()
    }

    /**
     * 自定义登录接口
     */
    @SystemLog(value = "登录", type = SystemLog.Type.LOGIN)
    @ApiOperation("登录")
    @PostMapping("login")
    fun login(@Validated @RequestBody logUser: LoginUser): String {
        return this.authService.login(logUser)
    }

    @SystemLog(value = "登出", type = SystemLog.Type.LOGOUT)
    @ApiOperation("登出")
    @DeleteMapping("logout")
    fun logout(): Boolean {
        return true
    }
}

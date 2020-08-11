package com.fpwag.admin.application.service

import cn.hutool.captcha.LineCaptcha
import cn.hutool.captcha.generator.MathGenerator
import com.fpwag.admin.domain.dto.output.ImageCodeVO
import com.fpwag.admin.domain.dto.input.LoginUser
import com.fpwag.admin.infrastructure.config.SsoProperties
import com.fpwag.admin.infrastructure.security.token.service.TokenService
import com.fpwag.boot.core.exception.Assert
import com.fpwag.boot.core.utils.IdUtils
import com.fpwag.boot.data.redis.RedisOperator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 授权相关业务处理
 *
 * @author fpwag
 */
@Service
class AuthService {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(AuthService::class.java)
    }

    @Autowired
    private lateinit var properties: SsoProperties
    @Autowired
    private lateinit var tokenService: TokenService
    @Autowired
    private lateinit var systemService: SystemService
    @Autowired
    private lateinit var redisOperation: RedisOperator

    private lateinit var captcha: LineCaptcha

    /**
     * hutool 图片验证码
     */
    fun getCode(): ImageCodeVO {
        captcha = LineCaptcha(111, 36, 5, 50)
        captcha.generator = MathGenerator(1)
        val code = captcha.code // 此code并不是计算后的结果，而是一个算术表达式
        val uuid: String = this.properties.imageCode.codeKey + IdUtils.snowflakeId()

        if (LOGGER.isInfoEnabled) {
            LOGGER.info("请求[$uuid]的验证码是：$code")
        }

        this.redisOperation.set(uuid, code, this.properties.imageCode.expiration)
        return ImageCodeVO(img = "data:image/png;base64,${captcha.imageBase64}", uuid = uuid)
    }

    /**
     * 登录业务处理
     *
     * @param loginUser 登录参数，后续添加短信验证码登录等方式
     * @return token 包含头标识"Bearer "
     */
    fun login(loginUser: LoginUser): String {
        // 验证码检查
        if (this.properties.imageCode.enable) {
            val code = this.redisOperation.get(loginUser.uuid) as? String
            this.redisOperation.del(loginUser.uuid)
            Assert.isTrue(!code.isNullOrBlank(), "验证码不存在或已过期")
            Assert.isTrue(!loginUser.code.isNullOrBlank() && captcha.generator.verify(code, loginUser.code), "验证码错误")
        }

        val user = this.systemService.getUserInfo(account = loginUser.username, protectedPwd = true)
        val token = this.tokenService.generateToken(user, loginUser.rememberMe)

        if (LOGGER.isInfoEnabled) {
            LOGGER.info("用户${loginUser.username}登录成功！token: $token")
        }

        return "${this.properties.jwt.tokenPrefix}$token"
    }
}
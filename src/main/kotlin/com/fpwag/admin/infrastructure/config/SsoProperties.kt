package com.fpwag.admin.infrastructure.config

import com.fpwag.admin.infrastructure.CommonConstant
import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * 系统属性配置<br>
 * 包括图形验证码（imageCode）、短信验证码（smsCode）和jwt等配置
 *
 * @author fpwag
 */
@ConfigurationProperties(prefix = "sso")
class SsoProperties {
    companion object {
        private val ENDPOINTS = arrayOf(
                "/favicon.ico",
                "/v2/api-docs/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", // swagger
                "/auth/**", "/error"
        )
    }

    /**
     * Security whitelist configuration.
     */
    var ignoreUrls: Array<String> = arrayOf()
        get() {
            if (field.isEmpty()) {
                return ENDPOINTS
            }

            val list = arrayListOf<String>()
            list.addAll(ENDPOINTS)
            list.addAll(field)
            return list.toTypedArray()
        }

    /**
     * image code
     */
    var imageCode: Code = Code(CommonConstant.IMG_CODE_KEY)

    /**
     * sms code
     */
    var smsCode: Code = Code(CommonConstant.SMS_CODE_KEY)

    /**
     * jwt
     */
    var jwt: JwtProperties = JwtProperties()

    /**
     * cors跨越配置
     */
    var cors: Cors = Cors()

    inner class Cors {
        /**
         * Enable global cors filter.
         */
        var enable: Boolean = false
        /**
         * cors allow origin.
         */
        var allowOrigin: String? = null
        /**
         * cors Access-Control-Allow-Methods
         * @see CommonConstant.ALLOW_METHODS
         */
        var allowMethods: String = CommonConstant.ALLOW_METHODS
        /**
         * cors Access-Control-Allow-Credentials, default true
         */
        var credentials: Boolean = true
        /**
         * cors Access-Control-Allow-Headers
         */
        var allowHeaders: String? = null
        /**
         * cors Access-Control-Expose-Headers, default Location.
         */
        var exposeHeaders: String = CommonConstant.EXPOSE_HEADERS
    }

    inner class Code(var codeKey: String) {
        /**
         * Enable imageCode or smsCode. default enabled.
         */
        var enable: Boolean = false
        /**
         * expiration. default 5 min.
         */
        var expiration: Long = 300L
    }

    inner class JwtProperties {
        /**
         * authorize name, default Authorization.
         */
        var authorizeName: String = CommonConstant.DEFAULT_AUTHORIZE
        /**
         * token prefix, default `Bearer `
         */
        var tokenPrefix: String = CommonConstant.DEFAULT_TOKEN_PREFIX
        /**
         * token expiration, default 1800L(30min), unit: second
         */
        var expiration: Long = CommonConstant.DEFAULT_TOKEN_EXPIRATION
        /**
         * remember me token expiration, default 604800L(one week), unit: second
         */
        var rememberExpiration: Long = CommonConstant.DEFAULT_REMEMBER_ME_EXPIRATION
        /**
         * token secret key, cannot be null.
         */
        var secretKey: String = CommonConstant.DEFAULT_SECRET
        /**
         * token encode algorithm, default hs256.
         */
        var algorithm: String = CommonConstant.DEFAULT_ALGORITHM
        /**
         * jwt subject name.
         */
        var subject: String = CommonConstant.DEFAULT_SUBJECT
    }
}
package com.fpwag.admin.infrastructure.security

import cn.hutool.json.JSONUtil
import com.fpwag.boot.core.R
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 处理认证相关异常
 *
 * @author fpwag
 */
@Component
class FpwagAuthenticationEntryPoint : AuthenticationEntryPoint {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    override fun commence(p0: HttpServletRequest, p1: HttpServletResponse, p2: AuthenticationException?) {
        this.logger.error("用户未认证[${p2?.message}]，不能访问服务器资源[${p0.requestURI} ${p0.method}]")
        p1.status = 401
        p1.contentType = MediaType.APPLICATION_JSON_VALUE
        p1.writer.write(JSONUtil.toJsonStr(R.fail<String>("用户未认证！")))
    }
}
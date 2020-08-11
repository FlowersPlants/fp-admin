package com.fpwag.admin.infrastructure.security.handler

import cn.hutool.json.JSONUtil
import com.fpwag.boot.core.R
import com.fpwag.boot.core.constants.CommonConstants
import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 处理权限不足
 *
 * @author fpwag
 */
@Component
class SsoAccessDeniedHandler : AccessDeniedHandler {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    override fun handle(req: HttpServletRequest, res: HttpServletResponse, e: AccessDeniedException) {
        this.logger.warn("接口请求 [${req.requestURI} ${req.method}] 访问受限[${e.message}]")
        res.status = 403
        res.contentType = CommonConstants.DEFAULT_CONTENT_TYPE
        res.writer.write(JSONUtil.toJsonStr(R.fail<String>("接口访问受限！")))
    }
}

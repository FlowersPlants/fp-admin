package com.fpwag.admin.infrastructure

import com.fpwag.admin.infrastructure.config.FpAdminProperties
import org.springframework.core.Ordered
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 解决跨域问题，可动态设置allow-origin
 *
 * @author fpwag
 */
class GlobalCorsWebFilter(private var cors: FpAdminProperties.Cors) : OncePerRequestFilter(), Ordered {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val method = HttpMethod.resolve(request.method)
        if (cors.enable && logger.isDebugEnabled) {
            logger.debug("doFilterInternal  url:${request.requestURI} ,method:$method")
        }
        response.setHeader("Access-control-Allow-Origin", cors.allowOrigin ?: request.getHeader("Origin"))
        response.setHeader("Access-Control-Allow-Methods", cors.allowMethods)
        response.setHeader("Access-Control-Allow-Credentials", cors.credentials.toString())
        response.setHeader("Access-Control-Allow-Headers", cors.allowHeaders
                ?: request.getHeader("Access-Control-Request-Headers"))
        if (method != HttpMethod.POST && method != HttpMethod.PUT) {
            if (method == HttpMethod.OPTIONS) {
                response.status = HttpStatus.OK.value()
                response.flushBuffer()
                return
            }
        } else {
            response.setHeader("Access-Control-Expose-Headers", cors.exposeHeaders)
        }

        filterChain.doFilter(request, response)
    }

    override fun getOrder(): Int {
        return Ordered.HIGHEST_PRECEDENCE + 10
    }
}

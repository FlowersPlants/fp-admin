package com.fpwag.admin.infrastructure.security

import com.fpwag.admin.infrastructure.config.FpAdminProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * 自定义鉴权过滤器
 * https://blog.csdn.net/ech13an/article/details/80779973
 *
 * @author FlowersPlants
 * @since v1
 */
class SecurityAuthorizationFilter(private var properties: FpAdminProperties) : OncePerRequestFilter() {
    @Autowired
    private lateinit var tokenProvider: TokenProvider

    private var jwt = this.properties.jwt

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        this.properties.ignoreUrls.forEach {
            if (AntPathRequestMatcher(it).matcher(request).isMatch) {
                chain.doFilter(request, response)
                return // 忽略的url直接跳过
            }
        }

        val token = this.resolveToken(request)
        if (!token.isNullOrBlank()) {
            SecurityContextHolder.getContext().authentication = this.tokenProvider.parseToken(token)
        }
        chain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(this.jwt.authorizeName)
        if (!bearerToken.isNullOrBlank() && bearerToken.startsWith(this.jwt.tokenPrefix)) {
            return bearerToken.substring(this.jwt.tokenPrefix.length)
        } else {
            logger.warn("非法Token：$bearerToken")
        }
        return null
    }
}

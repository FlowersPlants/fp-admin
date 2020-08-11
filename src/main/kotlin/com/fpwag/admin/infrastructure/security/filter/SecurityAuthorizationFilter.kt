package com.fpwag.admin.infrastructure.security.filter

import com.fpwag.admin.infrastructure.config.FpAdminProperties
import com.fpwag.admin.infrastructure.security.SecurityUser
import com.fpwag.admin.infrastructure.security.token.service.TokenService
import com.fpwag.boot.core.exception.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
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
    private lateinit var tokenService: TokenService

    private var jwt: FpAdminProperties.JwtProperties = this.properties.jwt

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        this.properties.ignoreUrls.forEach {
            if (AntPathRequestMatcher(it).matcher(request).isMatch) {
                chain.doFilter(request, response)
                return // 忽略的url直接跳过
            }
        }

        val authorizeName = request.getHeader(this.jwt.authorizeName)
        if (authorizeName == null || !authorizeName.startsWith(this.jwt.tokenPrefix)) {
            chain.doFilter(request, response)
            return // 没有token时直接返回，而不是抛出异常
        }

        SecurityContextHolder.getContext().authentication = this.getAuthentication(authorizeName)
        chain.doFilter(request, response)
    }

    /**
     * 解析token，返回AuthenticationToke
     */
    @Throws(Exception::class)
    private fun getAuthentication(tokenHeader: String): Authentication? {
        val token = tokenHeader.substring(this.jwt.tokenPrefix.length)
        Assert.isTrue(token.isNotBlank() && !this.tokenService.expire(token), "token无效或已过期")

        val user = this.tokenService.parseToken(token)
        Assert.isTrue(user != null, "token无效或已过期")

        val info = user!!.userInfo
        val authorities = AuthorityUtils.createAuthorityList(*(user.authorities.toTypedArray()))
        val securityUser = SecurityUser(info.id, info.deptId, info.admin, info.username, info.password, authorities)
        return UsernamePasswordAuthenticationToken(securityUser, info.password, authorities)
    }
}

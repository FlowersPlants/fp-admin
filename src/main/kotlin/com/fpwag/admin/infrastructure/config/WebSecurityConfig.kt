package com.fpwag.admin.infrastructure.config

import com.fpwag.admin.infrastructure.security.filter.SecurityAuthorizationFilter
import com.fpwag.admin.infrastructure.security.handler.SsoAccessDeniedHandler
import com.fpwag.admin.infrastructure.security.handler.SsoAuthenticationEntryPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var properties: FpAdminProperties
    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    @Qualifier("securityUserDetailsService")
    private lateinit var userDetailsService: UserDetailsService

    /**
     * 注入userDetailsService
     */
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(this.userDetailsService)?.passwordEncoder(this.passwordEncoder)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun securityAuthorizationFilter(): SecurityAuthorizationFilter {
        return SecurityAuthorizationFilter(this.properties)
    }

    /**
     * http访问配置，配置各个路径的访问权限
     * 登录和登出都是覆盖默认的
     */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        val registry = http.exceptionHandling()
                .accessDeniedHandler(SsoAccessDeniedHandler())
                .authenticationEntryPoint(SsoAuthenticationEntryPoint())
                .and().authorizeRequests()

        // 白名单配置
        this.properties.ignoreUrls.forEach { registry.antMatchers(it).permitAll() }

        registry.anyRequest().authenticated()  // 其他任何请求，登录后可访问

                .and().formLogin().disable() // 禁用表单登录

                .logout().permitAll()  // 配置登出相关

                .and().csrf().disable() // 关闭csrf

                .sessionManagement().disable() // 基于token，所以不需要session

                .addFilterBefore(this.securityAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }
}
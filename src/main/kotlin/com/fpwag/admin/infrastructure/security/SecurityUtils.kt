package com.fpwag.admin.infrastructure.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

/**
 * 认证相关工具类
 *
 * @author fpwag
 */
object SecurityUtils {
    /**
     * 获取当前登录信息
     */
    fun getAuthentication(): Authentication? {
        return SecurityContextHolder.getContext().authentication
    }

    /**
     * 获取当前登录用户账号信息
     */
    fun getUsername(): String? {
        return getAuthentication()?.name
    }
}
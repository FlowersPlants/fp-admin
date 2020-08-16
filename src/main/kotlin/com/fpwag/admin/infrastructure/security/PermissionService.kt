package com.fpwag.admin.infrastructure.security

import org.springframework.stereotype.Component
import org.springframework.util.PatternMatchUtils

/**
 * 自定义权限检查
 *
 * @author fpwag
 */
@Component("pms")
class PermissionService {
    /**
     * 判断接口是否有xxx:xxx权限
     *
     * @param permissions 权限列表
     * @return {boolean}
     */
    fun hasPermission(vararg permissions: String): Boolean {
        if (permissions.isEmpty()) return false

        val authorities = SecurityUtils.getAuthentication()?.authorities?.mapNotNull { it.authority } ?: return false
        return authorities.contains("admin") || authorities.any { x: String -> PatternMatchUtils.simpleMatch(permissions, x) }
    }
}
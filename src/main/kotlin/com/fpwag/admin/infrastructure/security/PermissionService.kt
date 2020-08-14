package com.fpwag.admin.infrastructure.security

import cn.hutool.core.util.StrUtil
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import org.springframework.util.PatternMatchUtils

@Component("pms")
class PermissionService {
    /**
     * 判断接口是否有xxx:xxx权限
     *
     * @param permissions 权限
     * @return {boolean}
     */
    fun hasPermission(vararg permissions: String): Boolean {
        if (permissions.isEmpty()) {
            return false
        }

        val authorities = SecurityUtils.getAuthentication()?.authorities ?: return false
        return authorities.stream()
                .map { obj: GrantedAuthority -> obj.authority }
                .filter(StrUtil::isNotBlank)
                .anyMatch { x: String -> PatternMatchUtils.simpleMatch(permissions, x) }
    }
}
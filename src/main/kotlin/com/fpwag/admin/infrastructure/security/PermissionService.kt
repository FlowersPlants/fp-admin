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
     * @param permission 权限
     * @return {boolean}
     */
    fun hasPermission(permission: String?): Boolean {
        if (StrUtil.isBlank(permission)) {
            return false
        }

        val user = SecurityUtils.getSecurityUser() ?: return false
        val authorities = user.authorities
        return authorities.stream()
                .map { obj: GrantedAuthority -> obj.authority }
                .filter(StrUtil::isNotBlank)
                .anyMatch { x: String? -> PatternMatchUtils.simpleMatch(permission, x) }
    }
}
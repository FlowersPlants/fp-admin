package com.fpwag.admin.infrastructure.security.token

import com.fpwag.boot.core.cqrs.DTO

/**
 * 用户信息聚合实体，返回用户角色、权限等信息
 *
 * @author fpwag
 */
data class TokenInfo(var userInfo: UserInfo, var authorities: List<String>) : DTO()
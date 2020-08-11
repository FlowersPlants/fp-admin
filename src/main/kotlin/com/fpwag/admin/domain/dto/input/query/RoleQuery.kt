package com.fpwag.admin.domain.dto.input.query

import com.fpwag.boot.data.mybatis.dto.BaseQuery

/**
 * 角色分页查询参数
 *
 * @author fpwag
 */
class RoleQuery : BaseQuery() {
    var code: String? = null
    var level: Int? = null
}
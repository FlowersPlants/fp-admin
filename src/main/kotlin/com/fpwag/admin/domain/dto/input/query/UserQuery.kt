package com.fpwag.admin.domain.dto.input.query

import com.fpwag.admin.infrastructure.mybatis.base.BaseQuery

/**
 * 用户信息分页查询参数<br>
 * keyword字段仅包括用户真实姓名和备注查询
 *
 * @author fpwag
 */
class UserQuery() : BaseQuery() {
    var id: String? = null
    var deptId: String? = null
    var username: String? = null
    var email: String? = null
    var mobile: String? = null

    constructor(id: String? = null, username: String? = null, email: String? = null, mobile: String? = null) : this() {
        this.id = id
        this.username = username
        this.email = email
        this.mobile = mobile
    }
}
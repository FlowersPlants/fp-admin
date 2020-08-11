package com.fpwag.admin.domain.dto.input.query

import com.fpwag.boot.data.mybatis.dto.BaseQuery

/**
 * 用户信息分页查询参数<br>
 * keyword字段仅包括用户真实姓名和备注查询
 *
 * @author fpwag
 */
class UserQuery : BaseQuery() {
    var roleId: String? = null
    var username: String? = null
    var email: String? = null
    var mobile: String? = null
}
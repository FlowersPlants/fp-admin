package com.fpwag.admin.domain.dto.input.query

import com.fpwag.admin.infrastructure.mybatis.base.BaseQuery

class DeptQuery : BaseQuery() {
    var parentId: String? = null
    var code: String? = null
}
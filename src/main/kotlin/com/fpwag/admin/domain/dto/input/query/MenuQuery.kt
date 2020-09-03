package com.fpwag.admin.domain.dto.input.query

import com.fpwag.admin.infrastructure.mybatis.base.BaseQuery

class MenuQuery : BaseQuery() {
    var parentId: String? = null
    var name: String? = null
    var path: String? = null
}
package com.fpwag.admin.domain.dto.input.query

import com.fpwag.boot.data.mybatis.dto.BaseQuery

class MenuQuery : BaseQuery() {
    var parentId: String? = null
    var name: String? = null
    var path: String? = null
}
package com.fpwag.admin.domain.dto.input.query

import com.fpwag.boot.data.mybatis.dto.BaseQuery

class DeptQuery : BaseQuery() {
    var parentId: String? = null
    var name: String? = null
}
package com.fpwag.admin.domain.dto.input.query

import com.fpwag.boot.core.cqrs.Query

class MenuQuery : Query() {
    var parentId: String? = null
    var name: String? = null
    var path: String? = null

    var startTime: String? = null
    var endTime: String? = null
}
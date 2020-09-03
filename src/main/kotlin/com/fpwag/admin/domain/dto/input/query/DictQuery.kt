package com.fpwag.admin.domain.dto.input.query

import com.fpwag.admin.infrastructure.mybatis.base.BaseQuery

/**
 * 字典分页查询参数
 *
 * @author fpwag
 */
class DictQuery : BaseQuery() {
    var code: String? = null
}
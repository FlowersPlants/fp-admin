package com.fpwag.admin.domain.dto.input.query

import com.fpwag.boot.data.mybatis.dto.BaseQuery

/**
 * 字典详情查询参数
 *
 * @author fpwag
 */
class DictItemQuery : BaseQuery() {
    var dictId: String? = null
}
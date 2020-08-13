package com.fpwag.admin.domain.dto.input.query

import com.fpwag.admin.infrastructure.mybatis.support.dto.BaseQuery

/**
 * 日志分页查询参数
 *
 * @author fwpag
 */
class LogQuery : BaseQuery() {
    var type: Int? = null                   // 日志类型
    var method: String? = null
}
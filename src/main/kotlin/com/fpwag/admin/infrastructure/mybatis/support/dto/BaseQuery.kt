package com.fpwag.admin.infrastructure.mybatis.support.dto

/**
 * 分页查询基础参数封装
 *
 * @author fpwag
 */
abstract class BaseQuery {
    // 查询字段值，一般包括remarks和name/title等字符串
    var keyword: String? = null

    // 创建时间段查询
    var startTime: String? = null
    var endTime: String? = null
}
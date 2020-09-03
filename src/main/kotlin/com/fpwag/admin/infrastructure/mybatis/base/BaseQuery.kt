package com.fpwag.admin.infrastructure.mybatis.base

/**
 * 分页查询基础参数封装
 *
 * @author fpwag
 */
abstract class BaseQuery {
    // 查询字段值，一般包括remarks和name/title等字符串
    var keyword: String? = null

    // 创建时间段查询
    var createTime: Array<String> = arrayOf()
}
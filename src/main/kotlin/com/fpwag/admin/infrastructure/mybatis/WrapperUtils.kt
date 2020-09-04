package com.fpwag.admin.infrastructure.mybatis

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.toolkit.Wrappers
import com.fpwag.admin.infrastructure.mybatis.base.BaseQuery

/**
 * mybatis plus wrapper包装工具
 *
 * @author fpwag
 */
object WrapperUtils {
    fun <T, Q : BaseQuery> build(query: Q?, columnName: String): QueryWrapper<T> {
        val wrapper = Wrappers.query<T>()
        wrapper.apply {
            query?.let {
                if (!it.keyword.isNullOrBlank()) {
                    if (!columnName.isBlank()) {
                        this.likeRight(columnName, it.keyword)
                        this.or()
                    }
                    this.likeRight("remarks", it.keyword)
                }
                if (it.createTime.isNotEmpty() && it.createTime.size == 2) {
                    this.between("create_time", it.createTime[0], it.createTime[1])
                }
            }
        }
        return wrapper
    }
}
package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.DictItemAddCmd
import com.fpwag.admin.domain.dto.input.command.DictItemEditCmd
import com.fpwag.admin.domain.dto.input.query.DictItemQuery
import com.fpwag.admin.domain.dto.output.DictItemDto
import com.fpwag.admin.infrastructure.mybatis.base.Service

/**
 * 字典项业务功能
 *
 * @author fpwag
 */
interface DictItemService : Service<DictItemQuery, DictItemDto> {
    /**
     * 字典项查询
     *
     * @param query 查询参数
     * @return 字典项集合
     */
    fun findList(query: DictItemQuery?): MutableList<DictItemDto>

    /**
     * 新增字典项
     *
     * @param command /
     */
    fun save(command: DictItemAddCmd)

    /**
     * 修改字典项
     *
     * @param command /
     */
    fun update(command: DictItemEditCmd)
}
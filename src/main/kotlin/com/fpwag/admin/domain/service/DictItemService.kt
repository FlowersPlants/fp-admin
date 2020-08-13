package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.DictItemAddCmd
import com.fpwag.admin.domain.dto.input.command.DictItemEditCmd
import com.fpwag.admin.domain.dto.input.query.DictItemQuery
import com.fpwag.admin.domain.dto.output.DictItemDto
import com.fpwag.admin.infrastructure.mybatis.support.Service

interface DictItemService : Service<DictItemDto> {
    fun findList(query: DictItemQuery?): MutableList<DictItemDto>

    fun save(command: DictItemAddCmd)

    fun update(command: DictItemEditCmd)
}
package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.DictItemAddCmd
import com.fpwag.admin.domain.dto.input.command.DictItemEditCmd
import com.fpwag.admin.domain.dto.input.query.DictItemQuery
import com.fpwag.admin.domain.dto.output.DictItemDto

interface DictItemService {
    fun findByDict(query: DictItemQuery): MutableList<DictItemDto>

    fun findById(id: String): DictItemDto?

    fun save(command: DictItemAddCmd)

    fun update(command: DictItemEditCmd)

    fun delete(ids: MutableSet<String>)
}
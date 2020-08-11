package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.DeptAddCmd
import com.fpwag.admin.domain.dto.input.command.DeptEditCmd
import com.fpwag.admin.domain.dto.input.query.DeptQuery
import com.fpwag.admin.domain.dto.output.DeptDto

interface DeptService {
    fun findList(query: DeptQuery?): MutableList<DeptDto>

    fun save(command: DeptAddCmd)

    fun update(command: DeptEditCmd)

    fun delete(ids: MutableSet<String>)
}
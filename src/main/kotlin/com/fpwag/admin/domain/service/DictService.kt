package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.DictAddCmd
import com.fpwag.admin.domain.dto.input.command.DictEditCmd
import com.fpwag.admin.domain.dto.input.query.DictQuery
import com.fpwag.admin.domain.dto.output.DictDto
import com.fpwag.boot.data.mybatis.PageResult
import org.springframework.data.domain.Pageable

/**
 * 字典相关业务接口
 *
 * @author FlowersPlants
 */
interface DictService {
    fun findPage(query: DictQuery?, pageable: Pageable): PageResult<DictDto>

    fun findAll(): MutableList<DictDto>

    fun findById(id: String): DictDto?

    fun save(command: DictAddCmd)

    fun update(command: DictEditCmd)

    fun delete(ids: MutableSet<String>)
}

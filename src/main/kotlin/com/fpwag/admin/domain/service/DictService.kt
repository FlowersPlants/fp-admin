package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.DictAddCmd
import com.fpwag.admin.domain.dto.input.command.DictEditCmd
import com.fpwag.admin.domain.dto.input.query.DictQuery
import com.fpwag.admin.domain.dto.output.DictDto
import com.fpwag.admin.infrastructure.mybatis.support.Service
import com.fpwag.boot.data.mybatis.PageResult
import org.springframework.data.domain.Pageable

/**
 * 字典相关业务接口
 *
 * @author FlowersPlants
 */
interface DictService : Service<DictDto> {
    fun findPage(query: DictQuery?, pageable: Pageable): PageResult<DictDto>

    fun findAll(): MutableList<DictDto>

    fun save(command: DictAddCmd)

    fun update(command: DictEditCmd)
}

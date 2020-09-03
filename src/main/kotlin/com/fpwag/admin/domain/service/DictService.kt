package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.DictAddCmd
import com.fpwag.admin.domain.dto.input.command.DictEditCmd
import com.fpwag.admin.domain.dto.input.query.DictQuery
import com.fpwag.admin.domain.dto.output.DictDto
import com.fpwag.admin.infrastructure.mybatis.base.Service

/**
 * 字典相关业务接口
 *
 * @author FlowersPlants
 */
interface DictService : Service<DictQuery, DictDto> {
    /**
     * 获取所有字典分类列表
     *
     * @return 字典列表
     */
    fun findAll(): MutableList<DictDto>

    /**
     * 新增字典
     *
     * @param command 字典信息
     */
    fun save(command: DictAddCmd)

    /**
     * 修改字典
     *
     * @param command 字典信息
     */
    fun update(command: DictEditCmd)
}

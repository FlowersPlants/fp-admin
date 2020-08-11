package com.fpwag.admin.domain.mapper

import com.fpwag.admin.domain.dto.input.command.DictItemAddCmd
import com.fpwag.admin.domain.dto.input.command.DictItemEditCmd
import com.fpwag.admin.domain.dto.output.DictItemDto
import com.fpwag.admin.domain.entity.DictItem
import com.fpwag.boot.core.BaseMapper
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

/**
 * 字典详情的dto和实体对象转换工具
 *
 * @author fpwag
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface DictItemMapper : BaseMapper<DictItem, DictItemDto> {
    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: DictItemAddCmd?): DictItem?

    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: DictItemEditCmd?): DictItem?
}
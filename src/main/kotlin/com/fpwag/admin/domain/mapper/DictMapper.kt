package com.fpwag.admin.domain.mapper

import com.fpwag.admin.domain.dto.input.command.DictAddCmd
import com.fpwag.admin.domain.dto.input.command.DictEditCmd
import com.fpwag.admin.domain.dto.output.DictDto
import com.fpwag.admin.domain.entity.Dict
import com.fpwag.boot.core.BaseMapper
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

/**
 * 字典的dto和实体对象转换工具
 *
 * @author fpwag
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface DictMapper : BaseMapper<Dict, DictDto> {
    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: DictAddCmd?): Dict?

    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: DictEditCmd?): Dict?
}
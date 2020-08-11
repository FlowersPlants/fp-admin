package com.fpwag.admin.domain.mapper

import com.fpwag.admin.domain.dto.input.command.DeptAddCmd
import com.fpwag.admin.domain.dto.input.command.DeptEditCmd
import com.fpwag.admin.domain.dto.output.DeptDto
import com.fpwag.admin.domain.entity.Dept
import com.fpwag.boot.core.BaseMapper
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface DeptMapper : BaseMapper<Dept, DeptDto> {
    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: DeptAddCmd?): Dept?

    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: DeptEditCmd?): Dept?
}
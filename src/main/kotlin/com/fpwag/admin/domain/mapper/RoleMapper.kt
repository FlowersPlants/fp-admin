package com.fpwag.admin.domain.mapper

import com.fpwag.admin.domain.dto.input.command.RoleAddCmd
import com.fpwag.admin.domain.dto.input.command.RoleEditCmd
import com.fpwag.admin.domain.dto.output.RoleDto
import com.fpwag.admin.domain.entity.Role
import com.fpwag.boot.core.BaseMapper
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

/**
 * 角色的dto和实体对象转换工具
 *
 * @author fpwag
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RoleMapper : BaseMapper<Role, RoleDto> {
    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: RoleAddCmd?): Role?

    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: RoleEditCmd?): Role?
}
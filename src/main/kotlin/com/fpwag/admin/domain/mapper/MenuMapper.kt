package com.fpwag.admin.domain.mapper

import com.fpwag.admin.domain.dto.input.command.MenuAddCmd
import com.fpwag.admin.domain.dto.input.command.MenuEditCmd
import com.fpwag.admin.domain.dto.output.MenuDto
import com.fpwag.admin.domain.dto.output.MenuTree
import com.fpwag.admin.domain.entity.Menu
import com.fpwag.boot.core.BaseMapper
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 * 菜单的dto和实体对象转换工具
 *
 * @author fpwag
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface MenuMapper : BaseMapper<Menu, MenuDto> {
    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: MenuAddCmd?): Menu?

    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: MenuEditCmd?): Menu?

    /**
     * 实体映射
     *
     * @param dto /
     * @return 实体对象
     */
    fun map(dto: MenuDto): MenuTree

    companion object {
        /**
         * 提供一个对象映射工具的实例，方便非spring环境使用
         */
        val INSTANCE: MenuMapper = Mappers.getMapper(MenuMapper::class.java)
    }
}
package com.fpwag.admin.domain.mapper

import com.fpwag.admin.domain.dto.input.command.UserAddCmd
import com.fpwag.admin.domain.dto.input.command.UserEditCmd
import com.fpwag.admin.domain.dto.output.UserDto
import com.fpwag.admin.domain.entity.User
import com.fpwag.boot.core.BaseMapper
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers

/**
 * 用户的dto和实体对象转换工具
 *
 * @author fpwag
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper : BaseMapper<User, UserDto> {
    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: UserAddCmd?): User?

    /**
     * 实体映射
     *
     * @param command /
     * @return 实体对象
     */
    fun map(command: UserEditCmd?): User?

    companion object {
        /**
         * 提供一个对象映射工具的实例，方便非spring环境使用
         */
        val INSTANCE: UserMapper = Mappers.getMapper(UserMapper::class.java)
    }
}
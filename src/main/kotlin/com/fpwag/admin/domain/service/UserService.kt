package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.UserCommand
import com.fpwag.admin.domain.dto.input.command.UserAddCmd
import com.fpwag.admin.domain.dto.input.command.UserEditCmd
import com.fpwag.admin.domain.dto.input.query.UserQuery
import com.fpwag.admin.domain.dto.output.UserDto
import com.fpwag.admin.infrastructure.mybatis.support.Service
import com.fpwag.boot.data.mybatis.PageResult
import org.springframework.data.domain.Pageable

/**
 * 用户相关接口
 * @author FlowersPlants
 * @since v1
 */
interface UserService : Service<UserDto> {
    fun findPage(query: UserQuery?, pageable: Pageable): PageResult<UserDto>

    /**
     * 返回包含密码等敏感信息
     */
    fun findByUsername(username: String?): UserDto?

    /**
     * 新增用户基本信息，后台接口
     *
     * @param command 基本信息
     */
    fun save(command: UserAddCmd)

    /**
     * 修改其他非必要信息
     *
     * @see #updateInfo
     */
    fun update(command: UserEditCmd)

    /**
     * 修改用户信息，包括邮箱、头像、手机号、密码等关键信息
     */
    fun updateInfo(command: UserCommand)
}

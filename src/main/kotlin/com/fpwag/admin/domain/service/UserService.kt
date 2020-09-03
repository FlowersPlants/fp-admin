package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.UserCommand
import com.fpwag.admin.domain.dto.input.command.UserResetPwdCmd
import com.fpwag.admin.domain.dto.input.command.UserAddCmd
import com.fpwag.admin.domain.dto.input.command.UserEditCmd
import com.fpwag.admin.domain.dto.input.query.UserQuery
import com.fpwag.admin.domain.dto.output.UserDto
import com.fpwag.admin.infrastructure.mybatis.base.Service

/**
 * 用户相关接口
 *
 * @author FlowersPlants
 * @since v1
 */
interface UserService : Service<UserQuery, UserDto> {
    /**
     * 获取某个用户的所有权限和角色集合
     *
     * @param username 用户名
     * @param admin 是否超级管理员，默认false
     * @return 权限列表
     */
    fun getAuthorities(username: String?, admin: Boolean = false): Collection<String>

    /**
     * 返回包含密码等敏感信息
     *
     * @param username 用户名
     * @return dto 用户信息，包含密码
     */
    fun findByUsername(username: String?): UserDto?

    /**
     * 返回某个角色下得所有用户列表
     *
     * @param rid 角色id
     * @return 用户列表
     */
    fun findByRole(rid: String): MutableList<UserDto>

    /**
     * 新增用户基本信息，后台接口
     *
     * @param command 基本信息
     */
    fun save(command: UserAddCmd)

    /**
     * 修改其他非必要信息
     *
     * @param command 更新信息请求参数
     * @see #updateInfo
     */
    fun update(command: UserEditCmd)

    /**
     * 修改用户信息，包括邮箱、头像、手机号、密码等关键信息
     *
     * @param command 修改数据请求参数
     */
    fun updateInfo(command: UserCommand)

    /**
     * 重置密码
     *
     * @param command 请求参数，可以同时重置多个用户得密码
     */
    fun resetPwd(command: UserResetPwdCmd): String
}

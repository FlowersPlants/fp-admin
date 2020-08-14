package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.RoleAddCmd
import com.fpwag.admin.domain.dto.input.command.RoleAssignCmd
import com.fpwag.admin.domain.dto.input.command.RoleAuthCmd
import com.fpwag.admin.domain.dto.input.command.RoleEditCmd
import com.fpwag.admin.domain.dto.input.query.RoleQuery
import com.fpwag.admin.domain.dto.output.RoleDto
import com.fpwag.admin.infrastructure.mybatis.support.Service

/**
 * 角色业务处理
 *
 * @author FlowersPlants
 * @since v1
 */
interface RoleService : Service<RoleQuery, RoleDto> {
    /**
     * 根据用户名获取角色列表
     *
     * @param username 用户名
     * @return MutableList<RoleDto> 角色列表
     */
    fun findByUsername(username: String?): MutableList<RoleDto>

    /**
     * 保存角色信息
     *
     * @param command 新增角色请求参数
     */
    fun save(command: RoleAddCmd)

    /**
     * 编辑角色信息
     *
     * @param command 修改角色请求参数
     */
    fun update(command: RoleEditCmd)

    /**
     * 角色授权菜单
     *
     * @param command 角色授权参数
     */
    fun authMenus(command: RoleAuthCmd)

    /**
     * 分配用户
     *
     * @param command 角色分配用户参数
     */
    fun assignUsers(command: RoleAssignCmd)
}

package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.RoleAddCmd
import com.fpwag.admin.domain.dto.input.command.RoleAuthCmd
import com.fpwag.admin.domain.dto.input.command.RoleEditCmd
import com.fpwag.admin.domain.dto.input.query.RoleQuery
import com.fpwag.admin.domain.dto.output.RoleDto
import com.fpwag.boot.data.mybatis.PageResult
import org.springframework.data.domain.Pageable

/**
 * 角色业务处理
 * @author FlowersPlants
 * @since v1
 */
interface RoleService {
    /**
     * 获取分页数据
     */
    fun findPage(query: RoleQuery?, pageable: Pageable): PageResult<RoleDto>

    /**
     * 根据id获取角色信息
     */
    fun findById(id: String): RoleDto?

    /**
     * 根据用户低获取角色列表
     */
    fun findByUserId(userId: String?): MutableList<RoleDto>

    /**
     * 保存角色信息
     */
    fun save(command: RoleAddCmd)

    /**
     * 编辑角色信息
     */
    fun update(command: RoleEditCmd)

    /**
     * 角色授权菜单
     */
    fun authMenus(command: RoleAuthCmd?)

    /**
     * 批量删除角色
     */
    fun delete(ids: MutableSet<String>)
}

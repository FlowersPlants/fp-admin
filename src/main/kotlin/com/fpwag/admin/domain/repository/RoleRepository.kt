package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.dto.input.command.RoleAssignCmd
import com.fpwag.admin.domain.dto.input.command.RoleAuthCmd
import com.fpwag.admin.domain.entity.Role
import org.springframework.stereotype.Repository

/**
 * 角色管理数据库操作接口
 *
 * @author FlowersPlants
 * @since v1
 */
@Repository
interface RoleRepository : BaseMapper<Role> {
    /**
     * 根据用户账号获取其所有角色
     *
     * @param username 用户名
     * @return 角色列表
     */
    fun selectByUsername(username: String): MutableList<Role>

    /**
     * 批量新增角色和菜单中间表记录
     *
     * @param command 角色授权参数
     * @return 操作成功数量
     */
    fun batchRoleMenus(command: RoleAuthCmd): Int

    /**
     * 批量新增角色和用户中间表记录
     *
     * @param command 用户分配参数
     * @return 操作成功数量
     */
    fun batchRoleUsers(command: RoleAssignCmd): Int

    /**
     * 批量新增之前先删除记录
     *
     * @param id 角色id
     * @return 操作成功数量
     */
    fun deleteRoleMenus(id: String): Int

    /**
     * 批量新增之前先删除记录
     *
     * @param id 角色id
     * @return 操作成功数量
     */
    fun deleteRoleUsers(id: String): Int
}

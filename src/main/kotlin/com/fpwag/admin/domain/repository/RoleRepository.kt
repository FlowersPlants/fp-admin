package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.dto.input.command.RoleAuthCmd
import com.fpwag.admin.domain.entity.Role
import org.springframework.stereotype.Repository

/**
 * 角色管理数据库操作接口
 * @author FlowersPlants
 * @since v1
 */
@Repository
interface RoleRepository : BaseMapper<Role> {
    /**
     * 根据用户ID获取其所有角色
     * @param userId 用户ID
     */
    fun selectByUserId(userId: String): MutableList<Role>

    /**
     * 批量新增之前先删除记录
     */
    fun deleteRoleMenuByRoleId(command: RoleAuthCmd): Int

    /**
     * 批量新增角色和菜单中间表记录
     */
    fun insertBatchMenuRecord(command: RoleAuthCmd): Int

    /**
     * 根据部门查询被关联的角色数量，判断部门是否与角色有关联
     */
    fun countByDeptIds(ids: MutableSet<String>)
}

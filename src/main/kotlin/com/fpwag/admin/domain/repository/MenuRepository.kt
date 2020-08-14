package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.entity.Menu
import org.springframework.stereotype.Repository

/**
 * 菜单管理数据库接口
 *
 * @author FlowersPlants
 * @since v1
 */
@Repository
interface MenuRepository : BaseMapper<Menu> {
    /**
     * 根据用户名查询其所有菜单列表
     *
     * @param username 用户名
     * @return 去重的菜单列表
     */
    fun selectByUsername(username: String): MutableSet<Menu>

    /**
     * 根据菜单id和自定义树结构查询函数来查找所有子节点，包括本身
     *
     * @param id 菜单id
     * @return 所有子节点列表
     */
    fun selectChildren(id: String): MutableList<Menu>
}

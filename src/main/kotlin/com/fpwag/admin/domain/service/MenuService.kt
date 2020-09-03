package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.MenuAddCmd
import com.fpwag.admin.domain.dto.input.command.MenuEditCmd
import com.fpwag.admin.domain.dto.input.query.MenuQuery
import com.fpwag.admin.domain.dto.output.MenuDto
import com.fpwag.admin.domain.dto.output.MenuTree
import com.fpwag.admin.infrastructure.mybatis.base.Service
import com.fpwag.boot.core.TreeNode
import com.fpwag.boot.core.constants.CommonConstants

/**
 * 菜单业务处理
 *
 * @author FlowersPlants
 * @since v1
 */
interface MenuService : Service<MenuQuery, MenuDto> {
    /**
     * 查找菜单列表
     *
     * @param query 菜单查询参数
     * @return MutableList<MenuDto> 菜单列表
     */
    fun findList(query: MenuQuery?): MutableList<MenuDto>

    /**
     * 返回子节点列表(直接子节点，不包括所有后代)
     *
     * @param id 父节点id
     * @return 菜单列表
     */
    fun findChildren(id: String): MutableList<MenuDto>

    /**
     * 根据角色id查询菜单列表
     *
     * @param rid 角色id
     */
    fun findByRole(rid: String): MutableList<MenuDto>

    /**
     * 根据用户名获取其菜单列表<br>
     * 如果用户是超级管理员则直接获取所有菜单
     *
     * @param username 用户名
     * @return 去重后的菜单列表
     */
    fun findByUsername(username: String?, admin: Boolean = false): MutableList<MenuDto>

    /**
     * 构建树结构菜单列表
     *
     * @param dtoList 菜单列表
     * @param comparator 排序函数，默认树节点的sort升序
     * @return 树结构列表
     */
    fun buildTree(dtoList: Collection<MenuDto>,
                  comparator: Comparator<TreeNode> = Comparator { o1, o2 -> o1.sort - o2.sort },
                  parentId: String = CommonConstants.ROOT_PARENT_ID): MutableList<MenuTree>

    /**
     * 保存菜单信息
     *
     * @param command 菜单信息
     */
    fun save(command: MenuAddCmd)

    /**
     * 编辑菜单信息
     *
     * @param command 菜单信息
     */
    fun update(command: MenuEditCmd)
}
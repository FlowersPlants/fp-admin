package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.MenuAddCmd
import com.fpwag.admin.domain.dto.input.command.MenuEditCmd
import com.fpwag.admin.domain.dto.input.query.MenuQuery
import com.fpwag.admin.domain.dto.output.MenuDto
import com.fpwag.admin.domain.dto.output.MenuTree
import com.fpwag.admin.infrastructure.mybatis.support.Service
import com.fpwag.boot.core.TreeNode
import com.fpwag.boot.core.constants.CommonConstants

/**
 * 菜单业务处理
 * @author FlowersPlants
 * @since v1
 */
interface MenuService : Service<MenuDto> {
    fun findList(query: MenuQuery?): MutableList<MenuDto>

    /**
     * 根据父节点获取所有子节点列表
     *
     * @param parentId 父节点id
     * @param allChild 是否搜索所有子节点（包括间接子节点）
     * @return 菜单列表
     */
    fun findByParentId(parentId: String = CommonConstants.ROOT_PARENT_ID, allChild: Boolean = false): MutableList<MenuDto>

    /**
     * 根据用户id获取其菜单列表
     *
     * @param userId 用户id
     * @return 去重后的菜单列表
     */
    fun findByUserId(userId: String?): MutableList<MenuDto>

    /**
     * 获取当前用户菜单列表
     */
    fun findMenus(): MutableList<MenuDto>

    /**
     * 获取所有菜单列表
     *
     * @return 菜单列表
     */
    fun findAll(): MutableList<MenuDto>

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
     * 保存角色信息
     */
    fun save(command: MenuAddCmd)

    /**
     * 编辑角色信息
     */
    fun update(command: MenuEditCmd)
}
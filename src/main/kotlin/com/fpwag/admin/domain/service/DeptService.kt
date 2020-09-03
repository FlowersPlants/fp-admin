package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.DeptAddCmd
import com.fpwag.admin.domain.dto.input.command.DeptEditCmd
import com.fpwag.admin.domain.dto.input.query.DeptQuery
import com.fpwag.admin.domain.dto.output.DeptDto
import com.fpwag.admin.infrastructure.mybatis.base.Service

/**
 * 部门管理业务
 *
 * @author fpwag
 */
interface DeptService : Service<DeptQuery, DeptDto> {
    /**
     * 部门查询
     *
     * @param query 查询参数
     * @return 部门列表
     */
    fun findList(query: DeptQuery?): MutableList<DeptDto>
//
//    /**
//     * 根据id获取同级和上级数据
//     *
//     * @param id 部门id
//     */
//    fun findSuperior(id: String?): MutableList<DeptTree>
//
//    /**
//     * 构建菜单树
//     *
//     * @param dtoList dto对象列表
//     */
//    fun buildTree(dtoList: MutableList<DeptDto>)

    /**
     * 新增部门
     *
     * @param command /
     */
    fun save(command: DeptAddCmd)

    /**
     * 修改部门
     *
     * @param command /
     */
    fun update(command: DeptEditCmd)
}
package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.command.DeptAddCmd
import com.fpwag.admin.domain.dto.input.command.DeptEditCmd
import com.fpwag.admin.domain.dto.input.query.DeptQuery
import com.fpwag.admin.domain.dto.output.DeptDto
import com.fpwag.admin.domain.dto.output.DeptTree
import com.fpwag.admin.infrastructure.mybatis.support.Service

interface DeptService : Service<DeptDto> {
    fun findList(query: DeptQuery?): MutableList<DeptDto>

    /**
     * 根据id获取同级和上级数据
     *
     * @param id 部门id
     */
    fun findSuperior(id: String?): MutableList<DeptTree>

    /**
     * 构建菜单树
     *
     * @param dtoList dto对象列表
     */
    fun buildTree(dtoList: MutableList<DeptDto>)

    fun save(command: DeptAddCmd)

    fun update(command: DeptEditCmd)
}
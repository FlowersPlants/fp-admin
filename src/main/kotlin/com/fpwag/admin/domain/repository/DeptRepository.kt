package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.entity.Dept
import org.springframework.stereotype.Repository

@Repository
interface DeptRepository : BaseMapper<Dept> {
    fun selectByRoleId(roleId: String): MutableSet<Dept>

    fun selectChildren(id: String): MutableList<Dept>
}
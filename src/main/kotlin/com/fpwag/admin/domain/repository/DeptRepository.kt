package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.entity.Dept
import org.springframework.stereotype.Repository

@Repository
interface DeptRepository : BaseMapper<Dept> {
}
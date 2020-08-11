package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.entity.Log
import org.springframework.stereotype.Repository

@Repository
interface LogRepository : BaseMapper<Log>
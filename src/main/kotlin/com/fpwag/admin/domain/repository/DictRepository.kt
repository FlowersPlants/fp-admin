package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.entity.Dict
import org.springframework.stereotype.Repository

@Repository
interface DictRepository : BaseMapper<Dict>
package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.entity.DictItem
import org.springframework.stereotype.Repository

@Repository
interface DictItemRepository : BaseMapper<DictItem>
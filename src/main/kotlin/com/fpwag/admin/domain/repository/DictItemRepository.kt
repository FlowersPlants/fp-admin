package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.dto.input.query.DictItemQuery
import com.fpwag.admin.domain.entity.DictItem
import org.springframework.stereotype.Repository

@Repository
interface DictItemRepository : BaseMapper<DictItem> {
    /**
     * 根据字典id或code查询字典详情列表
     *
     * @param query 查询参数
     * @return 字典详情列表
     */
    fun selectByDict(query: DictItemQuery): MutableList<DictItem>
}
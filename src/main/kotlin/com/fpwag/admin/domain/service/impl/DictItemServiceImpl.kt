package com.fpwag.admin.domain.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.fpwag.admin.domain.dto.input.command.DictItemAddCmd
import com.fpwag.admin.domain.dto.input.command.DictItemEditCmd
import com.fpwag.admin.domain.dto.input.query.DictItemQuery
import com.fpwag.admin.domain.dto.output.DictItemDto
import com.fpwag.admin.domain.entity.DictItem
import com.fpwag.admin.domain.mapper.DictItemMapper
import com.fpwag.admin.domain.repository.DictItemRepository
import com.fpwag.admin.domain.service.DictItemService
import com.fpwag.boot.core.exception.Assert
import com.fpwag.boot.core.utils.MapperUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@CacheConfig(cacheNames = ["dict_item_cache"])
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = [Exception::class])
class DictItemServiceImpl : DictItemService {
    @Autowired
    private lateinit var mapper: DictItemMapper

    @Autowired
    private lateinit var repository: DictItemRepository

    @Cacheable(key = "#p0.dictId")
    override fun findByDict(query: DictItemQuery): MutableList<DictItemDto> {
        val list = this.repository.selectList(QueryWrapper<DictItem>().apply {
            if (!query.dictId.isNullOrBlank()) {
                this.eq("dict_id", query.dictId)
            }
            this.orderByAsc("sort")
        })
        return MapperUtils.mapList(list) { this.mapper.toDto(it) }
    }

    @Cacheable
    override fun findById(id: String): DictItemDto? {
        val entity = this.repository.selectById(id)
        return this.mapper.toDto(entity)
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun save(command: DictItemAddCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.insert(entity)
        Assert.isTrue(flag > 0, "删除失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun update(command: DictItemEditCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.updateById(entity)
        Assert.isTrue(flag > 0, "删除失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun delete(ids: MutableSet<String>) {
        val flag = this.repository.deleteBatchIds(ids)
        Assert.isTrue(flag > 0, "删除失败")
    }
}
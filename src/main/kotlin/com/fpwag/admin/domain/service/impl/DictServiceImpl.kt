package com.fpwag.admin.domain.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.fpwag.admin.domain.dto.input.UpdateStatusCmd
import com.fpwag.admin.domain.dto.input.command.DictAddCmd
import com.fpwag.admin.domain.dto.input.command.DictEditCmd
import com.fpwag.admin.domain.dto.input.query.DictQuery
import com.fpwag.admin.domain.dto.output.DictDto
import com.fpwag.admin.domain.entity.Dict
import com.fpwag.admin.domain.mapper.DictMapper
import com.fpwag.admin.domain.repository.DictRepository
import com.fpwag.admin.domain.service.DictService
import com.fpwag.boot.core.exception.Assert
import com.fpwag.boot.data.mybatis.MybatisPageMapper
import com.fpwag.boot.data.mybatis.PageResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@CacheConfig(cacheNames = ["dict_cache"])
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = [Exception::class])
class DictServiceImpl : DictService {
    @Autowired
    private lateinit var mapper: DictMapper

    @Autowired
    private lateinit var repository: DictRepository

    @Cacheable
    override fun findPage(query: DictQuery?, pageable: Pageable): PageResult<DictDto> {
        val page = MybatisPageMapper.pageableToPage<Dict>(pageable)
        val entityPage = this.repository.selectPage(page, QueryWrapper<Dict>().apply {
            query?.let {
                if (!it.keyword.isNullOrBlank()) {
                    this.likeRight("name", it.keyword)
                    this.or()
                    this.likeRight("remarks", it.keyword)
                }
                if (!it.code.isNullOrBlank()) {
                    this.eq("code", it.code)
                }
                if (!it.startTime.isNullOrBlank() && !it.endTime.isNullOrBlank()) {
                    this.between("create_time", it.startTime, it.endTime)
                }
            }
            if (page.orders.isEmpty()) {
                this.orderByDesc("create_time")
            }
        })
        return PageResult.of(entityPage) { this.mapper.toDto(it) }
    }

    @Cacheable
    override fun findAll(): MutableList<DictDto> {
        val list = this.repository.selectList(null)
        return this.mapper.toDto(list)
    }

    @Cacheable(key = "'dict_id_' + #p0")
    override fun findById(id: String?): DictDto? {
        if (id.isNullOrBlank()) {
            return null
        }
        val entity = this.repository.selectById(id)
        return this.mapper.toDto(entity)
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun save(command: DictAddCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.insert(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun update(command: DictEditCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.updateById(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun updateStatus(command: UpdateStatusCmd) {
        val entity = Dict(command.id)
        entity.status = command.status
        val flag = this.repository.updateById(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun delete(ids: MutableSet<String>) {
        val flag = this.repository.deleteBatchIds(ids)
        Assert.isTrue(flag > 0, "删除失败")
    }
}

package com.fpwag.admin.domain.service.impl

import com.fpwag.admin.domain.dto.input.UpdateStatusCmd
import com.fpwag.admin.domain.dto.input.command.DeptAddCmd
import com.fpwag.admin.domain.dto.input.command.DeptEditCmd
import com.fpwag.admin.domain.dto.input.query.DeptQuery
import com.fpwag.admin.domain.dto.output.DeptDto
import com.fpwag.admin.domain.entity.Dept
import com.fpwag.admin.domain.mapper.DeptMapper
import com.fpwag.admin.domain.repository.DeptRepository
import com.fpwag.admin.domain.service.DeptService
import com.fpwag.admin.infrastructure.mybatis.QueryUtils
import com.fpwag.boot.core.exception.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@CacheConfig(cacheNames = ["dept_cache"])
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = [Exception::class])
class DeptServiceImpl : DeptService {
    @Autowired
    private lateinit var mapper: DeptMapper

    @Autowired
    private lateinit var repository: DeptRepository

    override fun findById(id: String?): DeptDto? {
        if (id.isNullOrBlank()) return null
        val entity = this.repository.selectById(id)
        return this.mapper.toDto(entity)
    }

    @Cacheable
    override fun findList(query: DeptQuery?): MutableList<DeptDto> {
        val wrapper = QueryUtils.build<Dept, DeptQuery>(query, "name").apply {
            query?.let {
                if (!it.parentId.isNullOrBlank()) {
                    this.eq("parent_id", it.parentId)
                }
            }
            this.orderByDesc("create_time")
        }
        val list = this.repository.selectList(wrapper)
        return this.mapper.toDto(list)
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun save(command: DeptAddCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.insert(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun update(command: DeptEditCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.updateById(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun updateStatus(command: UpdateStatusCmd) {
        val entity = Dept(command.id)
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
package com.fpwag.admin.domain.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.fpwag.admin.domain.dto.input.command.RoleAddCmd
import com.fpwag.admin.domain.dto.input.command.RoleAuthCmd
import com.fpwag.admin.domain.dto.input.command.RoleEditCmd
import com.fpwag.admin.domain.dto.input.query.RoleQuery
import com.fpwag.admin.domain.dto.output.RoleDto
import com.fpwag.admin.domain.entity.Role
import com.fpwag.admin.domain.mapper.RoleMapper
import com.fpwag.admin.domain.repository.RoleRepository
import com.fpwag.admin.domain.service.RoleService
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

/**
 * 角色service实现类
 * @author FlowersPlants
 * @since v1
 */
@Service
@CacheConfig(cacheNames = ["sys_role"])
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = [Exception::class])
class RoleServiceImpl : RoleService {
    @Autowired
    private lateinit var mapper: RoleMapper

    @Autowired
    private lateinit var repository: RoleRepository

    @Cacheable
    override fun findPage(query: RoleQuery?, pageable: Pageable): PageResult<RoleDto> {
        val page = MybatisPageMapper.pageableToPage<Role>(pageable)
        val entityPage = this.repository.selectPage(page, QueryWrapper<Role>().apply {
            query?.let {
                if (!it.keyword.isNullOrBlank()) {
                    this.likeRight("name", it.keyword)
                    this.or()
                    this.likeRight("remarks", it.keyword)
                }
                if (it.level != null) {
                    this.eq("level", it.level)
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

    @Cacheable(key = "#p0")
    override fun findById(id: String): RoleDto? {
        val entity = this.repository.selectById(id)
        return this.mapper.toDto(entity)
    }

    @Cacheable(key = "'user_id_' + #p0")
    override fun findByUserId(userId: String?): MutableList<RoleDto> {
        if (userId == null) return mutableListOf()
        val list = this.repository.selectByUserId(userId)
        return this.mapper.toDto(list)
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun save(command: RoleAddCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.insert(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun update(command: RoleEditCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.updateById(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @Transactional
    @CacheEvict(allEntries = true)
    override fun authMenus(command: RoleAuthCmd?) {
        command?.let {
            this.repository.deleteRoleMenuByRoleId(it)
            val i = this.repository.insertBatchMenuRecord(it)
            Assert.isTrue(i == it.menuIds?.size, "更新失败")
        }
    }

    /**
     * 1、某个角色有活动用户时（状态为no delete）是否不能删除
     * 2、某个角色无有效用户时是否需删除中间表记录
     */
    @CacheEvict(allEntries = true)
    @Transactional
    override fun delete(ids: MutableSet<String>) {
        val flag = this.repository.deleteBatchIds(ids)
        Assert.isTrue(flag == ids.size, "删除失败")
    }
}
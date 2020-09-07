package com.fpwag.admin.domain.service.impl

import com.fpwag.admin.domain.dto.input.UpdateStatusCmd
import com.fpwag.admin.domain.dto.input.command.RoleAddCmd
import com.fpwag.admin.domain.dto.input.command.RoleAssignCmd
import com.fpwag.admin.domain.dto.input.command.RoleAuthCmd
import com.fpwag.admin.domain.dto.input.command.RoleEditCmd
import com.fpwag.admin.domain.dto.input.query.RoleQuery
import com.fpwag.admin.domain.dto.output.RoleDto
import com.fpwag.admin.domain.entity.Role
import com.fpwag.admin.domain.mapper.RoleMapper
import com.fpwag.admin.domain.repository.RoleRepository
import com.fpwag.admin.domain.service.RoleService
import com.fpwag.admin.infrastructure.mybatis.WrapperUtils
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
@CacheConfig(cacheNames = ["sys_role"])
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = [Exception::class])
class RoleServiceImpl : RoleService {
    @Autowired
    private lateinit var mapper: RoleMapper

    @Autowired
    private lateinit var repository: RoleRepository

    @Cacheable
    override fun findPage(query: RoleQuery?, pageable: Pageable?): PageResult<RoleDto> {
        val page = MybatisPageMapper.pageableToPage<Role>(pageable)
        val wrapper = WrapperUtils.build<Role, RoleQuery>(query).apply {
            query?.let {
                if (it.level != null) {
                    this.eq("level", it.level)
                }
                if (!it.code.isNullOrBlank()) {
                    this.eq("code", it.code)
                }
            }
            if (page.orders.isEmpty()) {
                this.orderByDesc("create_time")
            }
        }
        val entityPage = this.repository.selectPage(page, wrapper)
        return PageResult.of(entityPage) { this.mapper.toDto(it) }
    }

    @Cacheable(key = "#p0")
    override fun findById(id: String?): RoleDto? {
        if (id.isNullOrBlank()) return null
        val entity = this.repository.selectById(id)
        return this.mapper.toDto(entity)
    }

    override fun findByUsername(username: String?): MutableList<RoleDto> {
        if (username == null) return mutableListOf()
        val list = this.repository.selectByUsername(username)
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

    @CacheEvict(allEntries = true)
    @Transactional
    override fun updateStatus(command: UpdateStatusCmd) {
        val entity = Role(command.id)
        entity.status = command.status
        val flag = this.repository.updateById(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @Transactional
    @CacheEvict(allEntries = true)
    override fun authMenus(command: RoleAuthCmd) {
        this.repository.deleteRoleMenus(command.id!!)
        val i = this.repository.batchRoleMenus(command)
        Assert.isTrue(i == command.menuIds?.size, "更新失败")
    }

    @Transactional
    @CacheEvict(allEntries = true)
    override fun assignUsers(command: RoleAssignCmd) {
        this.repository.deleteRoleUsers(command.id!!)
        val i = this.repository.batchRoleUsers(command)
        Assert.isTrue(i == command.userIds?.size, "更新失败")
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

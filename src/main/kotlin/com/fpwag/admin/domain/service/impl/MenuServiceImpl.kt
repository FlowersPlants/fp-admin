package com.fpwag.admin.domain.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.fpwag.admin.domain.dto.input.UpdateStatusCmd
import com.fpwag.admin.domain.dto.input.command.MenuAddCmd
import com.fpwag.admin.domain.dto.input.command.MenuEditCmd
import com.fpwag.admin.domain.dto.input.query.MenuQuery
import com.fpwag.admin.domain.dto.output.MenuDto
import com.fpwag.admin.domain.dto.output.MenuTree
import com.fpwag.admin.domain.entity.Menu
import com.fpwag.admin.domain.mapper.MenuMapper
import com.fpwag.admin.domain.repository.MenuRepository
import com.fpwag.admin.domain.service.MenuService
import com.fpwag.admin.infrastructure.mybatis.QueryUtils
import com.fpwag.boot.core.TreeNode
import com.fpwag.boot.core.exception.Assert
import com.fpwag.boot.core.utils.MapperUtils
import com.fpwag.boot.core.utils.TreeUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@CacheConfig(cacheNames = ["sys_menu"])
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = [Exception::class])
class MenuServiceImpl : MenuService {
    @Autowired
    private lateinit var mapper: MenuMapper

    @Autowired
    private lateinit var repository: MenuRepository

    @Cacheable(key = "'id_' + #p0")
    override fun findById(id: String?): MenuDto? {
        if (id.isNullOrBlank()) return null
        val entity = this.repository.selectById(id)
        return this.mapper.toDto(entity)
    }

    @Cacheable
    override fun findChildren(id: String): MutableList<MenuDto> {
        val list = this.repository.selectList(QueryWrapper<Menu>().apply {
            this.eq("parent_id", id)
            this.orderByAsc("sort")
        })
        return this.mapper.toDto(list)
    }

    @Cacheable(key = "'rid_' + #p0")
    override fun findByRole(rid: String): MutableList<MenuDto> {
        val list = this.repository.selectByRoleId(rid)
        return this.mapper.toDto(list)
    }

    override fun findByUsername(username: String?, admin: Boolean): MutableList<MenuDto> {
        if (username.isNullOrBlank()) return mutableListOf()
        if (admin) return this.findList(null)
        val list = this.repository.selectByUsername(username)
        return this.mapper.toDto(list)
    }

    @Cacheable
    override fun findList(query: MenuQuery?): MutableList<MenuDto> {
        val wrapper = QueryUtils.build<Menu, MenuQuery>(query, "name").apply {
            query?.let {
                if (!it.path.isNullOrBlank()) {
                    this.likeRight("path", it.path)
                }
                if (!it.parentId.isNullOrBlank()) {
                    this.eq("parent_id", it.parentId)
                }
                if (it.createTime.isNotEmpty() && it.createTime.size == 2) {
                    this.between("create_time", it.createTime[0], it.createTime[1])
                }
            }
            this.orderByDesc("create_time")
        }
        val list = this.repository.selectList(wrapper)
        return this.mapper.toDto(list)
    }

    @Cacheable
    override fun buildTree(dtoList: Collection<MenuDto>, comparator: Comparator<TreeNode>, parentId: String): MutableList<MenuTree> {
        if (dtoList.isEmpty()) {
            return mutableListOf()
        }
        var menuTree = MapperUtils.mapList(dtoList) { MenuMapper.INSTANCE.map(it) }
        // 两层循环建树
        menuTree = TreeUtils.build(menuTree, parentId)
        // 排序
        menuTree = menuTree.sortedWith(comparator)
        menuTree.forEach { it.sortChildren(comparator) }
        return menuTree
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun save(command: MenuAddCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.insert(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun update(command: MenuEditCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.updateById(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun updateStatus(command: UpdateStatusCmd) {
        val entity = Menu(command.id)
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

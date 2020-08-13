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

/**
 * 菜单service实现类
 * @author FlowersPlants
 * @since v1
 */
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
        if (id.isNullOrBlank()) {
            return null
        }
        val entity = this.repository.selectById(id)
        return this.mapper.toDto(entity)
    }

    @Cacheable
    override fun findByParentId(parentId: String, allChild: Boolean): MutableList<MenuDto> {
        return if (allChild) {
            val list = this.repository.selectChildren(parentId)
            this.mapper.toDto(list)
        } else {
            this.findList(parentId)
        }
    }

    @Cacheable(key = "'user_id_' + #p0")
    override fun findByUserId(userId: String?): MutableList<MenuDto> {
        if (userId.isNullOrBlank()) return mutableListOf()
        val list = this.repository.selectByUserId(userId)
        return this.mapper.toDto(list)
    }

    override fun findMenus(): MutableList<MenuDto> {
        // TODO
        return this.findAll()
    }

    @Cacheable
    override fun findList(query: MenuQuery?): MutableList<MenuDto> {
        val list = this.repository.selectList(QueryWrapper<Menu>().apply {
            query?.let {
                if (!it.name.isNullOrBlank()) {
                    this.likeRight("name", it.name)
                }
                if (!it.path.isNullOrBlank()) {
                    this.likeRight("path", it.path)
                }
                if (!it.parentId.isNullOrBlank()) {
                    this.eq("parent_id", it.parentId)
                }
                if (!it.startTime.isNullOrBlank() && !it.endTime.isNullOrBlank()) {
                    this.between("create_time", it.startTime, it.endTime)
                }
            }
            this.orderByDesc("create_time")
        })
        return this.mapper.toDto(list)
    }

    override fun findAll(): MutableList<MenuDto> {
        return this.findList(null)
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
        ids.forEach {
            val list = this.findByParentId(it, allChild = true)
            ids.addAll(list.mapNotNull { a -> a.id })
        }
        val flag = this.repository.deleteBatchIds(ids)
        Assert.isTrue(flag > 0, "删除失败")
    }

    /**
     * 仅获取当前节点的所有直接子节点列表，不包括本身
     */
    private fun findList(parentId: String): MutableList<MenuDto> {
        val list = this.repository.selectList(QueryWrapper<Menu>().apply {
            this.eq("parent_id", parentId)
        })
        return this.mapper.toDto(list)
    }
}

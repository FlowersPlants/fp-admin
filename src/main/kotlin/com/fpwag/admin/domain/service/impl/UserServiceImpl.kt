package com.fpwag.admin.domain.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.fpwag.admin.application.event.UserCreatedEvent
import com.fpwag.admin.domain.dto.input.UpdateStatusCmd
import com.fpwag.admin.domain.dto.input.UserCommand
import com.fpwag.admin.domain.dto.input.command.UserAddCmd
import com.fpwag.admin.domain.dto.input.command.UserEditCmd
import com.fpwag.admin.domain.dto.input.query.UserQuery
import com.fpwag.admin.domain.dto.output.UserDto
import com.fpwag.admin.domain.entity.User
import com.fpwag.admin.domain.mapper.UserMapper
import com.fpwag.admin.domain.repository.UserRepository
import com.fpwag.admin.domain.service.UserService
import com.fpwag.boot.autoconfigure.web.SpringContextHolder
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
 * 用户service实现类
 * @author FlowersPlants
 * @since v1
 */
@Service
@CacheConfig(cacheNames = ["sys_user"])
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = [Exception::class])
class UserServiceImpl : UserService {
    @Autowired
    private lateinit var mapper: UserMapper

    @Autowired
    private lateinit var repository: UserRepository

    @Cacheable(key = "'username_' + #p0")
    override fun findByUsername(username: String?): UserDto? {
        val entity = this.findOne(username = username)
        return this.mapper.toDto(entity)
    }

    @Cacheable(key = "'id_' + #p0")
    override fun findById(id: String?): UserDto? {
        val entity = this.findOne(id = id)
        return this.mapper.toDto(entity)
    }

    @Cacheable
    override fun findPage(query: UserQuery?, pageable: Pageable): PageResult<UserDto> {
        val page = MybatisPageMapper.pageableToPage<User>(pageable)
        val entityPage = this.repository.selectPage(page, QueryWrapper<User>().apply {
            query?.let {
                if (!it.keyword.isNullOrBlank()) {
                    this.likeRight("name", it.keyword)
                    this.or()
                    this.likeRight("remarks", it.keyword)
                }
                if (!it.deptId.isNullOrBlank()) {
                    this.eq("dept_id", it.deptId)
                }
                if (!it.username.isNullOrBlank()) {
                    this.likeRight("username", it.username)
                }
                if (!it.email.isNullOrBlank()) {
                    this.likeRight("email", it.email)
                }
                if (!it.mobile.isNullOrBlank()) {
                    this.likeRight("mobile", it.mobile)
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

    @CacheEvict(allEntries = true)
    @Transactional
    override fun save(command: UserAddCmd) {
        val entity = this.mapper.map(command) ?: return
        if (entity.status == null) {
            entity.status = true // 默认正常
        }
        val flag = this.repository.insert(entity)
        Assert.isTrue(flag > 0, "更新失败")

        // 发布用户创建成功事件。设置默认密码
        SpringContextHolder.publishEvent(UserCreatedEvent(entity))
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun update(command: UserEditCmd) {
        val entity = this.mapper.map(command)
        val flag = this.repository.updateById(entity)
        Assert.isTrue(flag > 0, "更新失败")
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun updateInfo(command: UserCommand) {
        val user = User(command.id)
        when (command.type) {
            UserCommand.Type.PWD -> user.password = command.value
            UserCommand.Type.EMAIL -> user.email = command.value
            UserCommand.Type.MOBILE -> user.mobile = command.value
            UserCommand.Type.AVATAR -> user.avatar = command.value
        }
        this.repository.updateById(user)
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun updateStatus(command: UpdateStatusCmd) {
        val entity = User(command.id)
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

    /**
     * 根据用户唯一信息获取唯一用户
     *
     * @param username 用户名
     * @param email 邮箱
     * @param mobile 手机号
     * @return 用户信息
     */
    private fun findOne(id: String? = null, username: String? = null, email: String? = null, mobile: String? = null): User? {
        if (id.isNullOrBlank() && username.isNullOrBlank() && email.isNullOrBlank() && mobile.isNullOrBlank()) {
            return null
        }
        return this.repository.selectOne(QueryWrapper<User>().apply {
            if (!id.isNullOrBlank()) {
                this.eq("id", id)
            }
            if (!username.isNullOrBlank()) {
                this.eq("username", username)
            }
            if (!email.isNullOrBlank()) {
                this.eq("email", email)
            }
            if (!mobile.isNullOrBlank()) {
                this.eq("mobile", mobile)
            }
        })
    }
}

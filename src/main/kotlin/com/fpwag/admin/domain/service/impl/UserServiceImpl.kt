package com.fpwag.admin.domain.service.impl

import cn.hutool.core.util.RandomUtil
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.fpwag.admin.domain.dto.input.UpdateStatusCmd
import com.fpwag.admin.domain.dto.input.command.UserAddCmd
import com.fpwag.admin.domain.dto.input.command.UserEditCmd
import com.fpwag.admin.domain.dto.input.command.UserResetPwdCmd
import com.fpwag.admin.domain.dto.input.command.UserUpdatePwdCmd
import com.fpwag.admin.domain.dto.input.query.UserQuery
import com.fpwag.admin.domain.dto.output.UserDto
import com.fpwag.admin.domain.entity.User
import com.fpwag.admin.domain.dto.UpdatePwdEvent
import com.fpwag.admin.domain.mapper.UserMapper
import com.fpwag.admin.domain.repository.UserRepository
import com.fpwag.admin.domain.service.MenuService
import com.fpwag.admin.domain.service.RoleService
import com.fpwag.admin.domain.service.UserService
import com.fpwag.admin.infrastructure.mybatis.WrapperUtils
import com.fpwag.admin.infrastructure.security.SecurityUtils
import com.fpwag.boot.autoconfigure.web.SpringContextHolder
import com.fpwag.boot.core.exception.Assert
import com.fpwag.boot.data.mybatis.MybatisPageMapper
import com.fpwag.boot.data.mybatis.PageResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.DigestUtils

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

    @Autowired
    private lateinit var roleService: RoleService

    @Autowired
    private lateinit var menuService: MenuService

    @Cacheable(key = "'username_' + #p0 + #p1==true ? '_1' : '_0'")
    override fun getAuthorities(username: String?, admin: Boolean): Collection<String> {
        val roles = this.roleService.findByUsername(username).mapNotNull { it.code?.toLowerCase() }
        val permissions = this.menuService.findByUsername(username, admin).mapNotNull { it.permission }
        return mutableListOf(*roles.toTypedArray(), *permissions.toTypedArray())
    }

    override fun findByUsername(username: String?): UserDto? {
        val entity = this.findOne(UserQuery(username = username))
        return this.mapper.toDto(entity)
    }

    @Cacheable(key = "'rid_' + #p0")
    override fun findByRole(rid: String): MutableList<UserDto> {
        val list = this.repository.selectByRoleId(rid)
        return this.mapper.toDto(list)
    }

    @Cacheable(key = "'id_' + #p0")
    override fun findById(id: String?): UserDto? {
        val entity = this.findOne(UserQuery(id = id))
        return this.mapper.toDto(entity)
    }

    @Cacheable
    override fun findPage(query: UserQuery?, pageable: Pageable?): PageResult<UserDto> {
        val page = MybatisPageMapper.pageableToPage<User>(pageable)
        val wrapper = WrapperUtils.build<User, UserQuery>(query).apply {
            query?.let {
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
            }
            if (page.orders.isEmpty()) {
                this.orderByDesc("create_time")
            }
        }
        val entityPage = this.repository.selectPage(page, wrapper)
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
        SpringContextHolder.publishEvent(UpdatePwdEvent(mutableListOf(entity)))
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
    override fun resetPwd(command: UserResetPwdCmd): String {
        var defaultPwd = "fpwag1234!"
        var encryptPassword = DigestUtils.md5DigestAsHex(defaultPwd.toByteArray())
        if (command.random) {
            defaultPwd = RandomUtil.randomString(12)
            encryptPassword = DigestUtils.md5DigestAsHex(defaultPwd.toByteArray())
        }
        val list = command.ids.map {
            User().apply {
                this.id = it
                this.password = encryptPassword
            }
        }
        SpringContextHolder.publishEvent(UpdatePwdEvent(list))
        return defaultPwd
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun updatePwd(command: UserUpdatePwdCmd) {
        val passwordEncode = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        val username = SecurityUtils.getUsername()
        val user = this.findOne(UserQuery(username = username))
        val old = DigestUtils.md5DigestAsHex(command.oldPass!!.toByteArray())
        Assert.isTrue(passwordEncode.matches(old, user?.password), "原密码不正确")

        user?.password = DigestUtils.md5DigestAsHex(command.newPass!!.toByteArray())
        SpringContextHolder.publishEvent(UpdatePwdEvent(mutableListOf(user)))
    }

    @CacheEvict(allEntries = true)
    @Transactional
    override fun updateAvatar(avatar: String) {
        val username = SecurityUtils.getUsername()
        val user = this.findOne(UserQuery(username = username))
        user?.avatar = avatar
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
     * @param query 查询参数
     * @return 用户信息
     */
    private fun findOne(query: UserQuery?): User? {
        return try {
            this.repository.selectOne(QueryWrapper<User>().apply {
                query?.let {
                    if (!it.id.isNullOrBlank()) {
                        this.eq("id", it.id)
                    }
                    if (!it.username.isNullOrBlank()) {
                        this.eq("username", it.username)
                    }
                    if (!it.email.isNullOrBlank()) {
                        this.eq("email", it.email)
                    }
                    if (!it.mobile.isNullOrBlank()) {
                        this.eq("mobile", it.mobile)
                    }
                }
            })
        } catch (e: Exception) {
            null
        }
    }
}

package com.fpwag.admin.application.event

import com.fpwag.admin.domain.entity.User
import com.fpwag.admin.domain.repository.UserRepository
import com.fpwag.boot.core.constants.CommonConstants
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 * spring event事件监听处理
 *
 * @author fpwag
 */
@Component
class CommandListener {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(CommandListener::class.java)
    }

    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @EventListener(value = [UserCreatedEvent::class])
    fun handle(event: UserCreatedEvent) {
        val entity = event.source as User
        entity.password = this.passwordEncoder.encode(CommonConstants.DEFAULT_USER_PWD)
        val i = this.repository.updateById(entity)
        if (logger.isInfoEnabled && i > 0) {
            logger.info("用户[${entity.username}]初始密码设置成功！")
        }
    }
}
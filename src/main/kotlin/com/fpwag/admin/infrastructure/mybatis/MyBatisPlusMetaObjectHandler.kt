package com.fpwag.admin.infrastructure.mybatis

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import com.fpwag.admin.infrastructure.security.SecurityUtils
import org.apache.ibatis.reflection.MetaObject
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

/**
 * mybatis plus公共字段自动填充处理器
 * @author FlowersPlants
 * @since v1
 */
class MyBatisPlusMetaObjectHandler : MetaObjectHandler {
    companion object {
        private val logger = LoggerFactory.getLogger(MyBatisPlusMetaObjectHandler::class.java)

        private fun getCurrentUsername(): String? {
            return try {
                SecurityUtils.getUsername()
            } catch (e: Exception) {
                logger.warn("Get current user[username] fail, cause message: {}", e.message)
                null
            }
        }
    }

    override fun insertFill(metaObject: MetaObject?) {
        val createBy: String? = getCurrentUsername()
        this.strictInsertFill(metaObject, "createBy", String::class.java, createBy)
                .strictInsertFill(metaObject, "createTime", LocalDateTime::class.java, LocalDateTime.now())
    }

    override fun updateFill(metaObject: MetaObject?) {
        val updateBy: String? = getCurrentUsername()
        this.strictUpdateFill(metaObject, "updateBy", String::class.java, updateBy)
                .strictUpdateFill(metaObject, "updateTime", LocalDateTime::class.java, LocalDateTime.now())
    }
}
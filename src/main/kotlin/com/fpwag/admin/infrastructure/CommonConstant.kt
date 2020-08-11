package com.fpwag.admin.infrastructure

/**
 * 公共常量
 * @author FlowersPlants
 * @since v1
 */
object CommonConstant {
    // authorize
    const val DEFAULT_AUTHORIZE = "Authorization"
    const val DEFAULT_TOKEN_PREFIX = "Bearer "
    // 过期时间 86400s，即1天
    const val DEFAULT_TOKEN_EXPIRATION = 86400L
    // 设置了记住我的过期时间是 604800s，即7天
    const val DEFAULT_REMEMBER_ME_EXPIRATION = 604800L
    // 密钥
    const val DEFAULT_SECRET = "wang@+!secret"
    // 加密算法
    const val DEFAULT_ALGORITHM = "hs256"
    // 默认的主体信息
    const val DEFAULT_SUBJECT = "subject"

    // redis key
    const val IMG_CODE_KEY = "sso-img-code-"
    const val SMS_CODE_KEY = "sso-sms-code-"

    // cors
    const val ALLOW_METHODS = "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH"
    const val EXPOSE_HEADERS = "Location"

    // other
    const val ADMIN_ID = "1" // 超级管理员的用户id和角色id都是1

    /**
     * 统一管理的序列化号
     */
    const val SERIAL_VERSION = 520L

    /**
     * 受保护的密码
     */
    const val PROTECTED_PWD = "N/A"
}

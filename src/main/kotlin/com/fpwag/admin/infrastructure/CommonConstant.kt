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
    // 加密算法
    const val DEFAULT_ALGORITHM = "hs512"

    // redis key
    const val IMG_CODE_KEY = "img-code-"
    const val SMS_CODE_KEY = "sms-code-"

    // cors
    const val ALLOW_METHODS = "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH"
    const val EXPOSE_HEADERS = "Location"

    /**
     * 统一管理的序列化号
     */
    const val SERIAL_VERSION = 520L

    /**
     * 受保护的密码
     */
    const val PROTECTED_PWD = "N/A"
}

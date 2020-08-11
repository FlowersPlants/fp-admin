package com.fpwag.admin.infrastructure

import com.fpwag.boot.logging.Logging
import com.fpwag.boot.logging.LoggingDetails

/**
 * 自定义log日志扩展实体
 *
 * @author fpwag
 */
class OperationLog : Logging {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var remoteAddr: String? = null
    var userAgent: String? = null

    constructor(remoteAddr: String?, userAgent: String?, traceId: String?, title: String?, type: Int, requestUri: String?, method: String?, params: String?, executeResult: String?, executeTime: Long)
            : super(traceId, title, type, requestUri, method, params, executeResult, executeTime) {
        this.remoteAddr = remoteAddr
        this.userAgent = userAgent
    }

    constructor(remoteAddr: String?, userAgent: String?, details: LoggingDetails)
            : super(details.traceId, details.title, details.type, details.requestUri, details.method, details.params, details.executeResult, details.executeTime) {
        this.remoteAddr = remoteAddr
        this.userAgent = userAgent
    }
}
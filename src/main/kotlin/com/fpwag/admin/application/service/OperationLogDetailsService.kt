package com.fpwag.admin.application.service

import cn.hutool.extra.servlet.ServletUtil
import com.fpwag.admin.domain.service.LogService
import com.fpwag.admin.infrastructure.OperationLog
import com.fpwag.boot.logging.LoggingDetails
import com.fpwag.boot.logging.LoggingDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*

@Component
class OperationLogDetailsService : LoggingDetailsService {
    @Autowired
    private lateinit var service: LogService

    override fun asyncSave(p0: LoggingDetails) {
        val request = (Objects.requireNonNull(RequestContextHolder.getRequestAttributes()) as ServletRequestAttributes).request
        val sysLog = OperationLog(ServletUtil.getClientIP(request), request.getHeader("User-Agent"), p0)
        this.service.save(sysLog)
    }
}
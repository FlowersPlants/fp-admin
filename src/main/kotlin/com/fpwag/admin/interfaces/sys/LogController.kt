package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.query.LogQuery
import com.fpwag.admin.domain.service.LogService
import com.fpwag.boot.logging.annotation.SystemLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SystemLog(value = "日志管理")
@Api(tags = ["日志管理"])
@RestController
@RequestMapping("/sys/log")
class LogController {
    @Autowired
    private lateinit var service: LogService

    @SystemLog(value = "日志分页", type = SystemLog.Type.QUERY)
    @ApiOperation("日志分页")
    @GetMapping
    @PreAuthorize("@pms.hasPermission('sys:log:list')")
    fun findPage(query: LogQuery, pageable: Pageable): Any? {
        return this.service.findPage(query, pageable)
    }
}
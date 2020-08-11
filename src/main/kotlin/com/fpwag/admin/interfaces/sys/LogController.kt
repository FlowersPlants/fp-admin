package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.query.LogQuery
import com.fpwag.admin.domain.service.LogService
import com.fpwag.boot.logging.annotation.SystemLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SystemLog(value = "日志管理")
@Api(tags = ["日志相关接口"])
@RestController
@RequestMapping("/sys/log")
class LogController {
    @Autowired
    private lateinit var service: LogService

    @SystemLog(value = "获取日志分页列表", type = SystemLog.Type.QUERY)
    @ApiOperation("字典日志分页接口")
    @GetMapping
    fun findPage(query: LogQuery, pageable: Pageable): Any? {
        return this.service.findPage(query, pageable)
    }
}
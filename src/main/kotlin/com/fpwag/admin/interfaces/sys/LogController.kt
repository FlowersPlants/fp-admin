package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.query.LogQuery
import com.fpwag.admin.domain.service.LogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 日志管理
 * tip: 不用记录操作日志
 *
 * @author fpwag
 */
@RestController
@RequestMapping("/sys/log")
class LogController {
    @Autowired
    private lateinit var service: LogService

    @GetMapping
    @PreAuthorize("@pms.hasPermission('sys:log:list')")
    fun findPage(query: LogQuery, pageable: Pageable): Any? {
        return this.service.findPage(query, pageable)
    }

    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('sys:log:del')")
    fun delete(): Any? {
        return this.service.clear()
    }
}
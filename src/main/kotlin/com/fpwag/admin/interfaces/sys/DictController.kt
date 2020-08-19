package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.command.DictAddCmd
import com.fpwag.admin.domain.dto.input.command.DictEditCmd
import com.fpwag.admin.domain.dto.input.query.DictQuery
import com.fpwag.admin.domain.dto.output.DictDto
import com.fpwag.admin.domain.service.DictService
import com.fpwag.boot.data.mybatis.PageResult
import com.fpwag.boot.logging.annotation.SystemLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@SystemLog(value = "字典管理")
@RestController
@RequestMapping(value = ["/sys/dict"])
class DictController {
    @Autowired
    private lateinit var service: DictService

    @PreAuthorize("@pms.hasPermission('sys:dict:list')")
    @SystemLog(value = "获取字典分页列表", type = SystemLog.Type.QUERY)
    @GetMapping
    fun page(query: DictQuery, pageable: Pageable): PageResult<DictDto> {
        return this.service.findPage(query, pageable)
    }

    @PreAuthorize("@pms.hasPermission('sys:dict:add')")
    @SystemLog(value = "新增字典", type = SystemLog.Type.INSERT)
    @PostMapping
    fun add(@Validated @RequestBody command: DictAddCmd): Any? {
        return this.service.save(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:dict:edit')")
    @SystemLog(value = "修改字典", type = SystemLog.Type.UPDATE)
    @PutMapping
    fun edit(@Validated @RequestBody command: DictEditCmd): Any? {
        return this.service.update(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:dict:del')")
    @SystemLog(value = "删除字典", type = SystemLog.Type.DELETE)
    @DeleteMapping
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }
}

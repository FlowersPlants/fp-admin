package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.command.DictItemAddCmd
import com.fpwag.admin.domain.dto.input.command.DictItemEditCmd
import com.fpwag.admin.domain.dto.input.query.DictItemQuery
import com.fpwag.admin.domain.dto.output.DictItemDto
import com.fpwag.admin.domain.service.DictItemService
import com.fpwag.boot.data.mybatis.PageResult
import com.fpwag.boot.logging.annotation.SystemLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@SystemLog(value = "字典详情管理")
@RestController
@RequestMapping("/sys/dict-item")
class DictItemController {
    @Autowired
    private lateinit var service: DictItemService

    @GetMapping
    fun findByDict(query: DictItemQuery): Any {
        val list = this.service.findList(query)
        return PageResult.of<DictItemDto>(list.size, list)
    }

    @PreAuthorize("@pms.hasPermission('sys:dict:add')")
    @SystemLog(value = "新增字典详情", type = SystemLog.Type.INSERT)
    @PostMapping
    fun add(@Validated @RequestBody command: DictItemAddCmd): Any? {
        return this.service.save(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:dict:edit')")
    @SystemLog(value = "修改字典详情", type = SystemLog.Type.UPDATE)
    @PutMapping
    fun edit(@Validated @RequestBody command: DictItemEditCmd): Any? {
        return this.service.update(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:dict:del')")
    @SystemLog(value = "删除字典详情", type = SystemLog.Type.DELETE)
    @DeleteMapping
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }
}
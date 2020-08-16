package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.command.DictItemAddCmd
import com.fpwag.admin.domain.dto.input.command.DictItemEditCmd
import com.fpwag.admin.domain.dto.input.query.DictItemQuery
import com.fpwag.admin.domain.dto.output.DictItemDto
import com.fpwag.admin.domain.service.DictItemService
import com.fpwag.boot.data.mybatis.PageResult
import com.fpwag.boot.logging.annotation.SystemLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@SystemLog(value = "字典详情管理")
@Api(tags = ["字典详情管理"])
@RestController
@RequestMapping("/sys/dict-item")
class DictItemController {
    @Autowired
    private lateinit var service: DictItemService

    @ApiOperation("字典详情查询")
    @GetMapping
    fun findByDict(query: DictItemQuery): Any {
        val list = this.service.findList(query)
        return PageResult.of<DictItemDto>(list.size, list)
    }

    @SystemLog(value = "新增字典详情", type = SystemLog.Type.INSERT)
    @ApiOperation("新增字典详情")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys:dict:add')")
    fun add(@Validated @RequestBody command: DictItemAddCmd): Any? {
        return this.service.save(command)
    }

    @SystemLog(value = "修改字典详情", type = SystemLog.Type.UPDATE)
    @ApiOperation("修改字典详情")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys:dict:edit')")
    fun edit(@Validated @RequestBody command: DictItemEditCmd): Any? {
        return this.service.update(command)
    }

    @SystemLog(value = "删除字典详情", type = SystemLog.Type.DELETE)
    @ApiOperation("删除字典详情")
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('sys:dict:del')")
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }
}
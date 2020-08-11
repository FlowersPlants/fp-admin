package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.command.DictItemAddCmd
import com.fpwag.admin.domain.dto.input.command.DictItemEditCmd
import com.fpwag.admin.domain.dto.input.query.DictItemQuery
import com.fpwag.admin.domain.dto.output.DictItemDto
import com.fpwag.admin.domain.service.DictItemService
import com.fpwag.boot.logging.annotation.SystemLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@SystemLog(value = "字典详情管理")
@Api(tags = ["字典详情接口"])
@RestController
@RequestMapping("/sys/dict-item")
class DictItemController {
    @Autowired
    private lateinit var service: DictItemService

    @SystemLog(value = "根据字典id或code获取字典详情", type = SystemLog.Type.QUERY)
    @ApiOperation("根据字典id或code获取字典详情")
    @GetMapping
    fun findByDict(query: DictItemQuery): Any {
        val list = this.service.findByDict(query)
        return mutableMapOf("records" to list, "total" to list.size)
    }

    @SystemLog(value = "根据id获取字典", type = SystemLog.Type.QUERY)
    @ApiOperation("根据id获取字典")
    @GetMapping("{id}")
    fun getById(@PathVariable("id") id: String): DictItemDto? {
        return this.service.findById(id)
    }

    @SystemLog(value = "新增字典信息", type = SystemLog.Type.INSERT)
    @ApiOperation("字典管理新增接口")
    @PostMapping
    fun add(@Validated @RequestBody command: DictItemAddCmd): Any? {
        return this.service.save(command)
    }

    @SystemLog(value = "根据id更新字典信息", type = SystemLog.Type.UPDATE)
    @ApiOperation("字典管理编辑接口")
    @PutMapping
    fun edit(@Validated @RequestBody command: DictItemEditCmd): Any? {
        return this.service.update(command)
    }

    @SystemLog(value = "批量删除字典", type = SystemLog.Type.DELETE)
    @ApiOperation("字典管理逻辑删除接口")
    @DeleteMapping
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }
}
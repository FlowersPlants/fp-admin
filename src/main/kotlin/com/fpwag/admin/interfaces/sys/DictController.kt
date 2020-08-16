package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.command.DictAddCmd
import com.fpwag.admin.domain.dto.input.command.DictEditCmd
import com.fpwag.admin.domain.dto.input.query.DictQuery
import com.fpwag.admin.domain.dto.output.DictDto
import com.fpwag.admin.domain.service.DictService
import com.fpwag.boot.data.mybatis.PageResult
import com.fpwag.boot.logging.annotation.SystemLog
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@SystemLog(value = "字典管理")
@Api(tags = ["字典管理"])
@RestController
@RequestMapping(value = ["/sys/dict"])
class DictController {
    @Autowired
    private lateinit var service: DictService

    @SystemLog(value = "获取字典分页列表", type = SystemLog.Type.QUERY)
    @ApiOperation("字典管理分页接口")
    @GetMapping
    @PreAuthorize("@pms.hasPermission('sys:dict:list')")
    fun page(query: DictQuery, pageable: Pageable): PageResult<DictDto> {
        return this.service.findPage(query, pageable)
    }
//
//    @SystemLog(value = "根据id获取字典", type = SystemLog.Type.QUERY)
//    @ApiOperation("根据id获取字典")
//    @GetMapping("{id}")
//    fun getById(@PathVariable("id") id: String): DictDto? {
//        return this.service.findById(id)
//    }

    @SystemLog(value = "新增字典", type = SystemLog.Type.INSERT)
    @ApiOperation("新增字典")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys:dict:add')")
    fun add(@Validated @RequestBody command: DictAddCmd): Any? {
        return this.service.save(command)
    }

    @SystemLog(value = "修改字典", type = SystemLog.Type.UPDATE)
    @ApiOperation("修改字典")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys:dict:edit')")
    fun edit(@Validated @RequestBody command: DictEditCmd): Any? {
        return this.service.update(command)
    }

    @SystemLog(value = "删除字典", type = SystemLog.Type.DELETE)
    @ApiOperation("删除字典")
    @DeleteMapping
    @PreAuthorize("@pms.hasPermission('sys:dict:del')")
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }
}

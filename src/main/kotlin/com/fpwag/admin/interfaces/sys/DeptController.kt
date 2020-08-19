package com.fpwag.admin.interfaces.sys

import com.fpwag.admin.domain.dto.input.command.DeptAddCmd
import com.fpwag.admin.domain.dto.input.command.DeptEditCmd
import com.fpwag.admin.domain.dto.input.query.DeptQuery
import com.fpwag.admin.domain.dto.output.DeptDto
import com.fpwag.admin.domain.service.DeptService
import com.fpwag.boot.data.mybatis.PageResult
import com.fpwag.boot.logging.annotation.SystemLog
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@SystemLog(value = "部门管理")
@RestController
@RequestMapping(value = ["/sys/dept"])
class DeptController {
    @Autowired
    private lateinit var service: DeptService

    @PreAuthorize("@pms.hasPermission('sys:dept:list', 'sys:user:list')")
    @SystemLog(value = "查询部门", type = SystemLog.Type.QUERY)
    @GetMapping
    fun find(query: DeptQuery?): PageResult<DeptDto> {
        val records = this.service.findList(query)
        return PageResult.of<DeptDto>(records.size, records)
    }

    @PreAuthorize("@pms.hasPermission('sys:dept:add')")
    @SystemLog(value = "新增部门", type = SystemLog.Type.INSERT)
    @PostMapping
    fun add(@Validated @RequestBody command: DeptAddCmd): Any? {
        return this.service.save(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:dept:edit')")
    @SystemLog(value = "修改部门", type = SystemLog.Type.UPDATE)
    @PutMapping
    fun edit(@Validated @RequestBody command: DeptEditCmd): Any? {
        return this.service.update(command)
    }

    @PreAuthorize("@pms.hasPermission('sys:dept:del')")
    @SystemLog(value = "删除部门", type = SystemLog.Type.DELETE)
    @DeleteMapping
    fun delete(@RequestBody ids: MutableSet<String>): Any? {
        return this.service.delete(ids)
    }
}
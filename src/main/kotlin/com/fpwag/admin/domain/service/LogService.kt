package com.fpwag.admin.domain.service

import com.fpwag.admin.domain.dto.input.query.LogQuery
import com.fpwag.admin.domain.dto.output.LogDto
import com.fpwag.admin.infrastructure.OperationLog
import com.fpwag.boot.data.mybatis.PageResult
import org.springframework.data.domain.Pageable

/**
 * 日志记录相关业务接口
 *
 * @author FlowersPlants
 */
interface LogService {
    fun findPage(query: LogQuery?, pageable: Pageable): PageResult<LogDto>

    fun save(log: OperationLog?)
}
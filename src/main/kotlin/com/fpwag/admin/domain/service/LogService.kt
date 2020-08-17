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
    /**
     * 日志查询
     *
     * @param query 查询参数
     * @param pageable 分页参数
     * @return PageResult<LogDto> 查询结果
     */
    fun findPage(query: LogQuery?, pageable: Pageable): PageResult<LogDto>

    /**
     * 保存日志记录
     *
     * @param log 操作日志
     */
    fun save(log: OperationLog?)

    /**
     * 清空日志，利用truncate操作，无法恢复，谨慎使用
     * tip: 无事务
     */
    fun clear()
}
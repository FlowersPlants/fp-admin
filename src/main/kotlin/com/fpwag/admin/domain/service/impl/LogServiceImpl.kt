package com.fpwag.admin.domain.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.fpwag.admin.domain.dto.input.query.LogQuery
import com.fpwag.admin.domain.dto.output.LogDto
import com.fpwag.admin.domain.entity.Log
import com.fpwag.admin.domain.mapper.LogMapper
import com.fpwag.admin.domain.repository.LogRepository
import com.fpwag.admin.domain.service.LogService
import com.fpwag.admin.infrastructure.OperationLog
import com.fpwag.boot.core.exception.Assert
import com.fpwag.boot.data.mybatis.MybatisPageMapper
import com.fpwag.boot.data.mybatis.PageResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = [Exception::class])
class LogServiceImpl : LogService {
    @Autowired
    private lateinit var mapper: LogMapper

    @Autowired
    private lateinit var repository: LogRepository

    override fun findPage(query: LogQuery?, pageable: Pageable): PageResult<LogDto> {
        val page = MybatisPageMapper.pageableToPage<Log>(pageable)
        val entityPage = this.repository.selectPage(page, QueryWrapper<Log>().apply {
            query?.let {
                if (!it.keyword.isNullOrBlank()) {
                    this.likeRight("title", it.keyword)
                }
                if (it.type != null) {
                    this.eq("type", it.type)
                }
                if (!it.method.isNullOrBlank()) {
                    this.eq("method", it.method)
                }
                if (!it.startTime.isNullOrBlank() && !it.endTime.isNullOrBlank()) {
                    this.between("create_time", it.startTime, it.endTime)
                }
            }
            if (page.orders.isEmpty()) {
                this.orderByDesc("create_time")
            }
        })
        return PageResult.of(entityPage) { this.mapper.toDto(it) }
    }

    @Transactional
    override fun save(log: OperationLog?) {
        val entity = this.mapper.map(log)
        val flag = this.repository.insert(entity)
        Assert.isTrue(flag > 0, "日志保存失败")
    }
}

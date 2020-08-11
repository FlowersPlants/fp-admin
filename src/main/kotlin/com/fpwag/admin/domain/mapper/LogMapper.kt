package com.fpwag.admin.domain.mapper

import com.fpwag.admin.domain.dto.output.LogDto
import com.fpwag.admin.domain.entity.Log
import com.fpwag.admin.infrastructure.OperationLog
import com.fpwag.boot.core.BaseMapper
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy


/**
 * 日志的dto和实体对象转换工具
 *
 * @author fpwag
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface LogMapper : BaseMapper<Log, LogDto> {
    /**
     * 实体映射
     *
     * @param log /
     * @return 实体对象
     */
    @Mapping(source = "traceId", target = "id")
    fun map(log: OperationLog?): Log?
}

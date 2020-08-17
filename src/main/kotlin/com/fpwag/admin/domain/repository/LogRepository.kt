package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.entity.Log
import org.apache.ibatis.annotations.Delete
import org.springframework.stereotype.Repository

@Repository
interface LogRepository : BaseMapper<Log> {
    /**
     * truncate 清空表，实际上是重新创建表结构，成功时返回值是0
     *
     * @return 成功时返回0
     */
    @Delete("truncate table sys_log")
    fun empty(): Int
}
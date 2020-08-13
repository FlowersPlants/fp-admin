package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.support.model.BaseEntity

/**
 * 系统日志实体
 */
@TableName("sys_log")
class Log : BaseEntity() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    @TableId(value = "id", type = IdType.INPUT)
    override var id: String? = null                  // 唯一请求号

    var title: String? = null               // 日志标题
    var requestUri: String? = null          // 请求URI
    var method: String? = null              // 操作方式
    var params: String? = null              // 操作提交的数据
    var executeResult: String? = null       // 执行结果
    var executeTime: Long? = null           // 执行所用时间
    var type: Int? = null                   // 日志类型

    var remoteAddr: String? = null          // 操作IP地址
    var userAgent: String? = null           // 用户代理
}

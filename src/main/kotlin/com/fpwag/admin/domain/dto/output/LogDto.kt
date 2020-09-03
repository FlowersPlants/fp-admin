package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.base.BaseDTO

/**
 * 日志信息输出
 *
 * @author fpwag
 */
class LogDto : BaseDTO() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

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
package com.fpwag.admin.domain.dto

import org.springframework.context.ApplicationEvent

/**
 * 修改密码事件源
 *
 * @author fpwag
 */
class UpdatePwdEvent(source: Any) : ApplicationEvent(source)
package com.fpwag.admin.application.event

import org.springframework.context.ApplicationEvent

/**
 * 修改密码事件
 *
 * @author fpwag
 */
class UpdatePwdEvent(source: Any) : ApplicationEvent(source)
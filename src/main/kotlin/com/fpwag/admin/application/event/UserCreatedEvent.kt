package com.fpwag.admin.application.event

import org.springframework.context.ApplicationEvent

/**
 * 定义一个spring event事件，处理用户创建完成事件（比如：创建完成后异步设置初始密码）
 *
 * @author fpwag
 */
class UserCreatedEvent(source: Any) : ApplicationEvent(source)
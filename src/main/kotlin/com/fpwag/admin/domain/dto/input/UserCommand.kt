package com.fpwag.admin.domain.dto.input

import com.fpwag.boot.core.cqrs.Command
import javax.validation.constraints.NotBlank

/**
 * 用户关键信息更新命令
 *
 * @author fpwag
 */
class UserCommand() : Command() {
    @NotBlank(message = "用户账号不能为空")
    lateinit var username: String

    lateinit var type: Type

    @NotBlank(message = "修改的字段 \${type} 值不能为空")
    lateinit var value: String  // 更新的字段值

    constructor(username: String, type: Type, value: String) : this() {
        this.username = username
        this.type = type
        this.value = value
    }

    /**
     * 更新的字段类型，password, email, mobile, avatar, lockFlag
     */
    enum class Type {
        PWD, EMAIL, MOBILE, AVATAR, FLAG
    }
}
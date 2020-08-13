package com.fpwag.admin.domain.dto.input

import javax.validation.constraints.NotBlank

/**
 * 用户关键信息更新命令
 *
 * @author fpwag
 */
class UserCommand() {
    @NotBlank(message = "用户id不能为空")
    lateinit var id: String

    lateinit var type: Type

    @NotBlank(message = "修改的字段 \${type} 值不能为空")
    lateinit var value: String  // 更新的字段值

    constructor(id: String, type: Type, value: String) : this() {
        this.id = id
        this.type = type
        this.value = value
    }

    /**
     * 更新的字段类型，password, email, mobile, avatar
     */
    enum class Type {
        PWD, EMAIL, MOBILE, AVATAR
    }
}
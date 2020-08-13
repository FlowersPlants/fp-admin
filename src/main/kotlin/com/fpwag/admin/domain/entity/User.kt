package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.support.model.DataEntity

/**
 * 用户实体
 * @author FlowersPlants
 * @since v1
 */
@TableName("sys_user")
class User() : DataEntity() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    constructor(id: String?) : this() {
        this.id = id
    }

    var deptId: String? = null

    /**
     * 用户账号，唯一
     */
    var username: String? = null

    var password: String? = null

    /**
     * 姓名
     */
    var name: String? = null

    var avatar: String? = null

    var email: String? = null

    var mobile: String? = null

    /**
     * 性别（0-保密，1-男，2-女）
     */
    var gender: Int? = null

    /**
     * 是否超级管理员
     */
    var admin: Boolean? = null
}
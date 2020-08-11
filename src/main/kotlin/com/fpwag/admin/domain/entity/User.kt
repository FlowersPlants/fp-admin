package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.data.mybatis.model.WholeModel

/**
 * 用户实体
 * @author FlowersPlants
 * @since v1
 */
@TableName("sys_user")
open class User() : WholeModel() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    var roleId: String? = null

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

    var sort: Int? = null

    var email: String? = null

    var mobile: String? = null

    /**
     * 性别（0-保密，1-男，2-女）
     */
    var gender: String? = null

    /**
     * 用户状态(1-正常，0-禁用)
     */
    var lockFlag: Boolean? = null
}
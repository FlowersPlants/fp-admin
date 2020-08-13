package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.support.model.DataEntity

/**
 * 角色实体
 * @author FlowersPlants
 * @since v1
 */
@TableName("sys_role")
class Role() : DataEntity() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    constructor(id: String?) : this() {
        this.id = id
    }

    /**
     * 角色名称
     */
    var name: String? = null

    /**
     * 角色编码
     */
    var code: String? = null

    /**
     * 角色级别，数字越小级别越高，0最高，默认999
     */
    var level: Int? = null
}
package com.fpwag.admin.domain.entity

import com.baomidou.mybatisplus.annotation.TableName
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.mybatis.base.DataEntity

/**
 * 菜单实体
 * @author FlowersPlants
 * @since v1
 */
@TableName("sys_menu")
class Menu() : DataEntity() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

    constructor(id: String?) : this() {
        this.id = id
    }

    /**
     * 父ID
     */
    var parentId: String? = null

    /**
     * 菜单名称
     */
    var name: String? = null

    /**
     * 权限编码
     * 例如：sys:menu:insert
     */
    var permission: String? = null

    /**
     * 菜单类型(0-目录，1-菜单，2-按钮)
     */
    var type: String? = null

    /**
     * 前端请求路径
     */
    var path: String? = null

    /**
     * 前端页面组件地址
     */
    var component: String? = null

    /**
     * 图标名称
     */
    var icon: String? = null

    /**
     * 是否隐藏 0-显示，1-隐藏
     */
    var hidden: Boolean? = null

    /**
     * 是否缓存
     */
    var cache: Boolean? = null
}

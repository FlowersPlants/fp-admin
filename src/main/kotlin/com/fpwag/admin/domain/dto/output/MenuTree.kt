package com.fpwag.admin.domain.dto.output

import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.boot.core.TreeNode
import java.time.LocalDateTime

/**
 * 菜单树结构对象
 * @author FlowersPlants
 * @since v1
 */
class MenuTree : TreeNode() {
    companion object {
        private const val serialVersionUID = CommonConstant.SERIAL_VERSION
    }

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

    var createTime: LocalDateTime? = null

    var createBy: String? = null
}

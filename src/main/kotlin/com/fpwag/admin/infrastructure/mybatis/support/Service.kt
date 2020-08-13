package com.fpwag.admin.infrastructure.mybatis.support

import com.fpwag.admin.domain.dto.input.UpdateStatusCmd
import com.fpwag.admin.infrastructure.mybatis.support.dto.BaseDTO

/**
 * 基础业务公共接口，提供常见操作，包括根据id获取、修改状态和批量删除操作
 *
 * @author fpwag
 */
interface Service<D : BaseDTO> {
    /**
     * 根据id查找数据信息，并返回dto对象
     *
     * @param id 业务id，为null时返回null
     * @return dto对象，id为null时返回null
     */
    fun findById(id: String?): D?

    /**
     * 更新状态
     *
     * @param command 请求参数，包括业务id和状态字段
     */
    fun updateStatus(command: UpdateStatusCmd)

    /**
     * 批量删除（逻辑删除）
     *
     * @param ids 业务id集合
     */
    fun delete(ids: MutableSet<String>)
}
package com.fpwag.admin.infrastructure.mybatis.support

import com.fpwag.admin.domain.dto.input.UpdateStatusCmd
import com.fpwag.admin.infrastructure.mybatis.support.dto.BaseDTO
import com.fpwag.admin.infrastructure.mybatis.support.dto.BaseQuery
import com.fpwag.boot.core.exception.CustomException
import com.fpwag.boot.data.mybatis.PageResult
import org.springframework.data.domain.Pageable

/**
 * 基础业务公共接口，提供常见操作，包括根据id获取、修改状态和批量删除操作<br>
 * 因为返回的结果基本都是dto对象，所以重新封装service公共接口
 *
 * @author fpwag
 */
interface Service<Q : BaseQuery, D : BaseDTO> {
    /**
     * 根据id查找数据信息，并返回dto对象
     *
     * @param id 业务id，为null时返回null
     * @return dto对象，id为null时返回null
     */
    fun findById(id: String?): D?

    /**
     * 分页接口，直接返回分页结果对象，这里提供默认实现（抛出未实现异常）<br>
     * 部门模块不需要此接口，所以定义为默认实现方法
     *
     * @param query 查询参数
     * @param pageable 分页参数
     * @return PageResult<D> 分页数据
     */
    fun findPage(query: Q?, pageable: Pageable?): PageResult<D> {
        throw CustomException("Method [findPage] not implemented.")
    }

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
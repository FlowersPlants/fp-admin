package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank

/**
 * 修改字典详情时的参数
 *
 * @see com.fpwag.admin.domain.entity.DictItem
 * @author fpwag
 */
class DictItemEditCmd {
    @NotBlank(message = "字典id不能为空")
    var id: String? = null

    var value: String? = null

    var label: String? = null

    var dictId: String? = null

    var sort: Int? = null

    var remarks: String? = null
}
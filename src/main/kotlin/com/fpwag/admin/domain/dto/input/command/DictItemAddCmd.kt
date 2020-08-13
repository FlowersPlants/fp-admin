package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank

/**
 * 新增字典详情时的参数
 *
 * @see com.fpwag.admin.domain.entity.DictItem
 * @author fpwag
 */
class DictItemAddCmd {
    @NotBlank(message = "字典值不能为空")
    var value: String? = null

    @NotBlank(message = "字典标签不能为空")
    var label: String? = null

    @NotBlank(message = "字典类型id不能为空")
    var dictId: String? = null

    var sort: Int? = null

    var remarks: String? = null
}
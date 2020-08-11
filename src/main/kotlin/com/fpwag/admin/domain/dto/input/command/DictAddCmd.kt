package com.fpwag.admin.domain.dto.input.command

import com.fpwag.boot.core.cqrs.Command
import javax.validation.constraints.NotBlank

/**
 * 新增字典时的参数
 *
 * @see com.fpwag.admin.domain.entity.Dict
 * @author fpwag
 */
class DictAddCmd : Command() {
    @NotBlank(message = "字典名称不能为空")
    var name: String? = null

    @NotBlank(message = "字典编码不能为空")
    var code: String? = null

    var sort: Int? = null

    var remarks: String? = null
}
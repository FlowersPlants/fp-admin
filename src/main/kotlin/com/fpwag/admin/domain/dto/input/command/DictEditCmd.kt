package com.fpwag.admin.domain.dto.input.command

import com.fpwag.boot.core.cqrs.Command
import javax.validation.constraints.NotBlank

/**
 * 修改字典时的参数
 *
 * @see com.fpwag.admin.domain.entity.Dict
 * @author fpwag
 */
class DictEditCmd : Command() {
    @NotBlank(message = "字典id不能为空")
    var id: String? = null

    var name: String? = null

    var code: String? = null

    var sort: Int? = null

    var remarks: String? = null
}
package com.fpwag.admin.domain.dto.input

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * 状态更新请求参数
 *
 * @author fpwag
 */
class UpdateStatusCmd {
    @NotBlank(message = "业务id不能为空")
    var id: String? = null

    @NotNull(message = "状态不能为null")
    var status: Boolean? = null
}
package com.fpwag.admin.domain.dto.input.command

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * 角色dto对象
 * @author FlowersPlants
 * @date 2018-12-18
 */
class RoleAssignCmd {
    /**
     * 角色ID
     */
    @NotBlank(message = "角色id不能为空")
    var id: String? = null

    /**
     * 用户ID集合
     */
    @NotNull(message = "分配用户不能为空")
    var userIds: MutableList<String>? = null
}

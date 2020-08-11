package com.fpwag.admin.domain.repository

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.fpwag.admin.domain.entity.User
import org.springframework.stereotype.Repository

/**
 * 用户管理数据访问接口，可调用xml配置方法，可使用Select注解
 * @author FlowersPlants
 * @since v1
 */
@Repository
interface UserRepository : BaseMapper<User>
package com.fpwag.admin

import com.fpwag.boot.autoconfigure.swagger.EnableFpwagSwagger
import com.fpwag.boot.swagger.ApiGroup
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * kotlin方式启动类
 * @author FlowersPlants
 * @since v1
 */
@EnableFpwagSwagger(
        title = "接口文档",
        description = "sso项目接口文档",
        groups = [
            ApiGroup(groupName = "授权认证api", packages = "com.fpwag.admin.interfaces.auth"),
            ApiGroup(groupName = "对象存储api", packages = "com.fpwag.admin.interfaces.oss"),
            ApiGroup(groupName = "系统管理api", packages = "com.fpwag.admin.interfaces.sys", enableAuthorize = true)
        ]
)
@MapperScan("com.fpwag.admin.domain.repository")
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

package com.fpwag.admin

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * kotlin方式启动类
 * @author FlowersPlants
 * @since v1
 */
@MapperScan("com.fpwag.admin.domain.repository")
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

package com.fpwag.admin.infrastructure.config

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import com.fpwag.admin.infrastructure.GlobalCorsWebFilter
import com.fpwag.admin.infrastructure.mybatis.MyBatisPlusMetaObjectHandler
import com.fpwag.boot.core.idgen.IdGenerator
import com.fpwag.boot.core.idgen.SnowflakeGenerator
import com.fpwag.boot.oss.persist.FileDetailsService
import com.fpwag.boot.oss.persist.provider.JdbcFileDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import javax.sql.DataSource

@Configuration
@AutoConfigureBefore(WebSecurityConfig::class)
@EnableConfigurationProperties(FpAdminProperties::class)
class FpAdminConfig {
    @Autowired
    private lateinit var dataSource: DataSource

    @Autowired
    private lateinit var properties: FpAdminProperties

    /**
     * 全局跨越处理过滤器
     */
    @Bean
    @ConditionalOnProperty(prefix = "fp-admin.cors", name = ["enable"], havingValue = "true")
    fun corsFilter(): GlobalCorsWebFilter {
        return GlobalCorsWebFilter(this.properties.cors)
    }

    /**
     * mybatis-plus自动设值处理工具
     */
    @Bean
    fun metaObjectHandler(): MetaObjectHandler {
        return MyBatisPlusMetaObjectHandler()
    }

    /**
     * spring boot 密码加密策略
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    /**
     * 注册id生成器的bean，用于logging日志记录的追踪号生成<br>
     * 可以不注册，此时会有一个默认的生成器用于生成id
     *
     * @see com.fpwag.boot.core.idgen.DelegatingIdGenerator
     */
    @Bean
    fun idGenerator(): IdGenerator {
        return SnowflakeGenerator()
    }

    /**
     * 对象存储时，文件详情保存策略
     */
    @Bean
    fun fileDetailsService(): FileDetailsService {
        return JdbcFileDetailsService(this.dataSource)
    }
}
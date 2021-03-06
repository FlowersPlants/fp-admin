package com.fpwag.admin.infrastructure.security

import cn.hutool.core.util.ObjectUtil
import com.fpwag.admin.infrastructure.CommonConstant
import com.fpwag.admin.infrastructure.config.FpAdminProperties
import com.fpwag.boot.core.utils.IdUtils
import com.fpwag.boot.data.redis.RedisOperator
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import java.util.stream.Collectors

@Component
class TokenProvider(private var properties: FpAdminProperties) : InitializingBean {
    private val jwt = this.properties.jwt

    private lateinit var jwtParser: JwtParser
    private lateinit var jwtBuilder: JwtBuilder

    @Autowired
    private lateinit var redisOperation: RedisOperator

    override fun afterPropertiesSet() {
        val keyBytes: ByteArray = Decoders.BASE64.decode(this.jwt.secretKey)
        val key: Key = Keys.hmacShaKeyFor(keyBytes)
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
        this.jwtBuilder = Jwts.builder()
                .signWith(key, SignatureAlgorithm.forName(this.jwt.algorithm))
    }

    /**
     * 创建token，不设置过期时间
     */
    fun createToken(authentication: Authentication): String {
        val authorities = authentication.authorities.stream()
                .map { obj: GrantedAuthority -> obj.authority }
                .collect(Collectors.joining(","))
        return this.jwtBuilder
                .setId(IdUtils.snowflakeId())
                .claim("authorities", authorities)
                .setSubject(authentication.name)
                .compact()
    }

    /**
     * 生成token
     *
     * @param user 用户
     * @param rememberMe 是否记住我
     */
    fun generateToken(user: SecurityUser, rememberMe: Boolean = false): String {
        val authorities = user.authorities.stream()
                .map { obj: GrantedAuthority -> obj.authority }
                .collect(Collectors.joining(","))
        val token = this.jwtBuilder
                .setId(user.id)
                .setSubject(user.username)
                .claim("authorities", authorities)
                .claim("deptId", user.deptId)
                .compact()
        val expire = if (rememberMe) this.jwt.rememberExpiration else this.jwt.expiration
        this.redisOperation.set("${CommonConstant.REDIS_JWT_TOKEN}${user.username}", token, expire)
        return token
    }

    /**
     * 解析token
     */
    fun parseToken(token: String): Authentication {
        val claims: Claims = this.getClaims(token)
        val authoritiesStr = claims["authorities"]
        val authorities: Collection<GrantedAuthority?> = if (ObjectUtil.isNotEmpty(authoritiesStr)) {
            val arr = authoritiesStr.toString().split(",").toTypedArray()
            Arrays.stream(arr)
                    .map { role: String? -> SimpleGrantedAuthority(role) }
                    .collect(Collectors.toList())
        } else {
            Collections.emptyList()
        }
        val principal = User(claims.subject, "N/A", authorities)
        return UsernamePasswordAuthenticationToken(principal, token, authorities)
    }

    private fun getClaims(token: String?): Claims {
        return this.jwtParser
                .parseClaimsJws(token)
                .body
    }
}
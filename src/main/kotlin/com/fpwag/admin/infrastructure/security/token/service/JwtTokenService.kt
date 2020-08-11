package com.fpwag.admin.infrastructure.security.token.service

import com.fpwag.admin.infrastructure.config.FpAdminProperties
import com.fpwag.admin.infrastructure.security.token.TokenInfo
import com.fpwag.boot.data.redis.jackson.JacksonHolder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

/**
 * jwt token业务处理，提供具体的token处理方法
 *
 * @author fpwag
 */
@Component
class JwtTokenService constructor(properties: FpAdminProperties) : AbstractTokenService(properties) {
    private var jwt: FpAdminProperties.JwtProperties = properties.jwt
    private val objectMapper = JacksonHolder.getInstance()

    override fun generateToken(token: TokenInfo, rememberMe: Boolean): String {
        val claims = mutableMapOf(
                "created" to Date(),
                this.jwt.subject to this.objectMapper.writeValueAsString(token)
        )
        return this.generateTokenFromClaims(claims, token.userInfo.username, rememberMe)
    }

    override fun refreshToken(token: String): String {
        val createdDate = this.clock.now()
        val claims = this.getAllClaimsFromToken(token)
        claims.issuedAt = createdDate
        claims.expiration = this.getExpireDate(createdDate.time)

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.forName(this.jwt.algorithm), this.jwt.secretKey)
                .compact()
    }

    override fun parseToken(token: String): TokenInfo? {
        val json = this.getAllClaimsFromToken(token)[this.jwt.subject] as? String
        return this.objectMapper.readValue(json, TokenInfo::class.java)
    }

    override fun expire(token: String): Boolean {
        val expiration: Date = this.getClaimFromToken(token, Function { it.expiration })
        return expiration.before(clock.now())
    }
}
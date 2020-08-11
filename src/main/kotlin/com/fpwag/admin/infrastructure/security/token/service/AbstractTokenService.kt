package com.fpwag.admin.infrastructure.security.token.service

import com.fpwag.admin.infrastructure.config.FpAdminProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Clock
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.impl.DefaultClock
import java.util.*
import java.util.function.Function

abstract class AbstractTokenService(private var properties: FpAdminProperties) : TokenService {
    private val jwt = this.properties.jwt
    protected val clock: Clock = DefaultClock.INSTANCE

    protected fun generateTokenFromClaims(claims: Map<String, Any>, subject: String, rememberMe: Boolean): String {
        val expiration = if (rememberMe) this.jwt.rememberExpiration else this.jwt.expiration
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(this.clock.now())
                .setSubject(subject)
                .setExpiration(this.getExpireDate(expiration))
                .signWith(SignatureAlgorithm.forName(this.jwt.algorithm), this.jwt.secretKey)
                .compact()
    }

    protected fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
                .setSigningKey(this.jwt.secretKey)
                .parseClaimsJws(token)
                .body
    }

    protected fun getExpireDate(expiration: Long): Date {
        return Date(this.clock.now().time + expiration * 1000)
    }

    protected fun <R> getClaimFromToken(token: String, claimsResolver: Function<Claims, R>): R {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }
}
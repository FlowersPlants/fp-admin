package com.fpwag.admin.infrastructure.security.token.service

import com.fpwag.admin.infrastructure.security.token.TokenInfo

/**
 * 自定义token生成校验服务，提供token生成、解析、校验和刷新功能
 *
 * @author fpwag
 */
interface TokenService {
    /**
     * 生成token
     *
     * @param token 授权用户信息
     * @param rememberMe 是否记住，默认记住时间为7天
     * @return token
     */
    fun generateToken(token: TokenInfo, rememberMe: Boolean = false): String

    /**
     * 刷新token，用旧的token来生成一个新的token
     *
     * @param token 旧的快过期的token
     * @return 新的token，如果是”记住我“，也需要处理
     */
    fun refreshToken(token: String): String

    /**
     * token解析成授权用户
     *
     * @param token 待解析token
     * @return 授权用户
     */
    fun parseToken(token: String): TokenInfo?

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 是否过期
     */
    fun expire(token: String): Boolean {
        return this.parseToken(token) == null
    }
}
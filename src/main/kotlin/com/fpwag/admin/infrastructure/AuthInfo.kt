package com.fpwag.admin.infrastructure

data class AuthInfo(var info: UserInfo, var authorities: Collection<String>)
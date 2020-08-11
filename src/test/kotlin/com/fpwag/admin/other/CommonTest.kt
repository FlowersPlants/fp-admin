package com.fpwag.admin.other

import cn.hutool.captcha.LineCaptcha
import cn.hutool.captcha.generator.MathGenerator
import cn.hutool.crypto.digest.DigestUtil
import org.junit.Assert
import org.junit.Test

class CommonTest {
    @Test
    fun genPwd() {
        val password = "fpwag1234!"
        val pwd = DigestUtil.md5Hex(password.toByteArray())
        println(pwd)
        Assert.assertEquals(pwd, "0eb069b01fe4e6e51bfbc0061a10f51d")
    }

    @Test
    fun genCode() {
        val captcha = LineCaptcha(111, 36, 5, 50)
        captcha.generator = MathGenerator(1)
        println("data:image/png;base64,${captcha.imageBase64}")
        Assert.assertFalse(captcha.verify(captcha.code))
    }

    @Test
    fun verifyCode() {
        val code = "2-5="
        val captcha = LineCaptcha(111, 36, 5, 50)
        captcha.generator = MathGenerator(1)
        Assert.assertTrue(captcha.generator.verify(code, "-3"))
    }
}
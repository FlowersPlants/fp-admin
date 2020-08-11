package com.fpwag.admin.application

import com.fpwag.admin.ApplicationTest
import com.fpwag.admin.application.service.SystemService
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class SystemServiceTest : ApplicationTest() {
    @Autowired
    private lateinit var service: SystemService

    @Test
    fun updatePwdTest() {
        val username = "admin"
        val oldPwd = "21232f297a57a5a743894a0e4a801fc3"
        val pwd = "e10adc3949ba59abbe56e057f20f883e"
        this.service.updatePwd(username, oldPwd, pwd)
    }
}
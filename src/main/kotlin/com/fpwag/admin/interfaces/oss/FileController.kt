package com.fpwag.admin.interfaces.oss

import com.fpwag.boot.autoconfigure.oss.StorageService
import com.fpwag.boot.core.constants.CommonConstants
import com.fpwag.boot.oss.OssMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping(value = ["/oss"])
class FileController {
    @Autowired
    private lateinit var storageService: StorageService

    @PostMapping("upload")
    @Throws(Exception::class)
    fun upload(@RequestBody @RequestParam("file") file: MultipartFile): OssMetadata {
        return this.storageService.upload(file)
    }

    @GetMapping("{key}")
    @Throws(Exception::class)
    fun download(@PathVariable("key") key: String, response: HttpServletResponse) {
        val metadata = this.storageService.getByKey(key)
        response.reset()
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, metadata.contentDisposition)
        response.contentType = CommonConstants.DEFAULT_CONTENT_TYPE
        this.storageService.download(key, response.outputStream)
    }
}
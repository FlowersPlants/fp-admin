package com.fpwag.admin.interfaces.oss

import com.fpwag.boot.autoconfigure.oss.StorageService
import com.fpwag.boot.oss.OssMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletResponse
import kotlin.jvm.Throws

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
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        this.storageService.download(key, response.outputStream)
    }
}
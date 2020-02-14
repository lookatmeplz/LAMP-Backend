package com.LAMP.LAMPBackend.api

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["1. User"])
@RestController
@RequestMapping("user")
class UserController {
    @GetMapping
    fun main(): String {
        return "main user"
    }
}
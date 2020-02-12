package com.LAMP.LAMPBackend.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController {
    @GetMapping
    fun main(): String {
        return "main user"
    }
}
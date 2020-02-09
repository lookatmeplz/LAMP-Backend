package com.LAMP.LAMPBackend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class LampController {
    @GetMapping("/")
    fun main(): String {
        return "main"
    }
}

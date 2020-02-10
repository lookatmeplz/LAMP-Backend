package com.LAMP.LAMPBackend.diary

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("diary")
class LampController {
    @GetMapping
    fun main(): String {
        return "main diary"
    }
}

package com.LAMP.LAMPBackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LampApplication

fun main(args: Array<String>) {
	runApplication<LampApplication>(*args)
}

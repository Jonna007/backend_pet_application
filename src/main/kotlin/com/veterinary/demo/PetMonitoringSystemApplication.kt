package com.veterinary.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PetMonitoringSystemApplication

fun main(args: Array<String>) {
	runApplication<PetMonitoringSystemApplication>(*args)
}

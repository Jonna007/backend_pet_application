package com.veterinary.demo.dto

import java.time.LocalDate
import java.time.LocalTime

data class VitalSignsDTO(
    val id: Long? = null,
    val temperature: Double,
    val heartRate: Int,
    val date: LocalDate,
    val time: LocalTime,
    val device: String,
    val petId: Long
)
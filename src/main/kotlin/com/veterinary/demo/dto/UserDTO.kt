package com.veterinary.demo.dto

import java.time.LocalDateTime

data class UserDTO(
    val id: Long? = null,
    val name: String,
    val email: String,
    val password: String? = null
)

data class UserResponseDTO(
    val id: Long,
    val name: String,
    val email: String,
    val registrationDate: LocalDateTime
)
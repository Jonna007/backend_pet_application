package com.veterinary.demo.dto

data class PetDTO(
    val id: Long? = null,
    val name: String,
    val species: String,
    val breed: String,
    val age: Int,
    val ownerdName: String,
    val userId: Long
)

data class PetResponseDTO(
    val id: Long,
    val name: String,
    val species: String,
    val breed: String,
    val age: Int,
    val ownerdName: String,
    val userId: Long
)
package com.veterinary.demo.repository

import com.veterinary.demo.model.Pet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PetRepository : JpaRepository<Pet, Long> {
    fun findByUserId(userId: Long): List<Pet>
}
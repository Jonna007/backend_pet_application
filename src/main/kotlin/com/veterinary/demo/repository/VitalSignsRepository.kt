package com.veterinary.demo.repository

import com.veterinary.demo.model.VitalSigns
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface VitalSignsRepository : JpaRepository<VitalSigns, Long> {
    fun findByPetIdAndDate(petId: Long, date: LocalDate): List<VitalSigns>
}
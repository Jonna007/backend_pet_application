package com.veterinary.demo.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "vital_signs")
data class VitalSigns(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val temperature: Double,

    @Column(name = "heart_rate", nullable = false)
    val heartRate: Int,

    @Column(nullable = false)
    val date: LocalDate,

    @Column(nullable = false)
    val time: LocalTime,

    @Column(nullable = false)
    val device: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id", nullable = false)
    val pet: Pet
)
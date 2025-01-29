package com.veterinary.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "pets")
data class Pet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val species: String,

    @Column(nullable = false)
    val breed: String,

    @Column(nullable = false)
    val age: Int,

    @Column(name = "ownerd_name", nullable = false)
    val ownerdName: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @OneToMany(mappedBy = "pet", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val vitalSigns: List<VitalSigns> = mutableListOf()
)
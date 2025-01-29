package com.veterinary.demo.controller

import com.veterinary.demo.dto.PetDTO
import com.veterinary.demo.dto.PetResponseDTO
import com.veterinary.demo.service.PetService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/pets")
class PetController(private val petService: PetService) {

    @PostMapping
    fun createPet(@RequestBody petDTO: PetDTO): ResponseEntity<PetResponseDTO> {
        val createdPet = petService.createPet(petDTO)
        return ResponseEntity(createdPet, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getPet(@PathVariable id: Long): ResponseEntity<PetResponseDTO> {
        val pet = petService.getPet(id)
        return ResponseEntity.ok(pet)
    }

    @GetMapping
    fun getAllPets(): ResponseEntity<List<PetResponseDTO>> {
        val pets = petService.getAllPets()
        return ResponseEntity.ok(pets)
    }

    @GetMapping("/user/{userId}")
    fun getPetsByUser(@PathVariable userId: Long): ResponseEntity<List<PetResponseDTO>> {
        val pets = petService.getPetsByUserId(userId)
        return ResponseEntity.ok(pets)
    }

    @PutMapping("/{id}")
    fun updatePet(@PathVariable id: Long, @RequestBody petDTO: PetDTO): ResponseEntity<PetResponseDTO> {
        val updatedPet = petService.updatePet(id, petDTO)
        return ResponseEntity.ok(updatedPet)
    }

    @DeleteMapping("/{id}")
    fun deletePet(@PathVariable id: Long): ResponseEntity<Unit> {
        petService.deletePet(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
package com.veterinary.demo.service

import com.veterinary.demo.dto.PetDTO
import com.veterinary.demo.dto.PetResponseDTO
import com.veterinary.demo.model.Pet
import com.veterinary.demo.repository.PetRepository
import com.veterinary.demo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.data.repository.findByIdOrNull

@Service
class PetService(
    private val petRepository: PetRepository,
    private val userRepository: UserRepository
) {

    fun createPet(petDTO: PetDTO): PetResponseDTO {
        val user = userRepository.findByIdOrNull(petDTO.userId)
            ?: throw RuntimeException("User not found")

        val pet = Pet(
            name = petDTO.name,
            species = petDTO.species,
            breed = petDTO.breed,
            age = petDTO.age,
            ownerdName = petDTO.ownerdName,
            user = user
        )
        val savedPet = petRepository.save(pet)
        return convertToResponseDTO(savedPet)
    }

    fun getPet(id: Long): PetResponseDTO {
        val pet = petRepository.findByIdOrNull(id) ?: throw RuntimeException("Pet not found")
        return convertToResponseDTO(pet)
    }

    fun getAllPets(): List<PetResponseDTO> {
        return petRepository.findAll().map { convertToResponseDTO(it) }
    }

    fun getPetsByUserId(userId: Long): List<PetResponseDTO> {
        return petRepository.findByUserId(userId).map { convertToResponseDTO(it) }
    }

    fun updatePet(id: Long, petDTO: PetDTO): PetResponseDTO {
        val existingPet = petRepository.findByIdOrNull(id) ?: throw RuntimeException("Pet not found")
        val user = userRepository.findByIdOrNull(petDTO.userId) ?: throw RuntimeException("User not found")

        val updatedPet = existingPet.copy(
            name = petDTO.name,
            species = petDTO.species,
            breed = petDTO.breed,
            age = petDTO.age,
            ownerdName = petDTO.ownerdName,
            user = user
        )
        return convertToResponseDTO(petRepository.save(updatedPet))
    }

    fun deletePet(id: Long) {
        if (petRepository.existsById(id)) {
            petRepository.deleteById(id)
        } else {
            throw RuntimeException("Pet not found")
        }
    }

    private fun convertToResponseDTO(pet: Pet): PetResponseDTO {
        return PetResponseDTO(
            id = pet.id,
            name = pet.name,
            species = pet.species,
            breed = pet.breed,
            age = pet.age,
            ownerdName = pet.ownerdName,
            userId = pet.user.id
        )
    }
}
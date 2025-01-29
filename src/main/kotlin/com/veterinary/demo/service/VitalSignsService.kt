package com.veterinary.demo.service

import com.veterinary.demo.dto.VitalSignsDTO
import com.veterinary.demo.model.VitalSigns
import com.veterinary.demo.repository.PetRepository
import com.veterinary.demo.repository.VitalSignsRepository
import org.springframework.stereotype.Service
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate

@Service
class VitalSignsService(
    private val vitalSignsRepository: VitalSignsRepository,
    private val petRepository: PetRepository
) {

    fun recordVitalSigns(vitalSignsDTO: VitalSignsDTO): VitalSignsDTO {
        val pet = petRepository.findByIdOrNull(vitalSignsDTO.petId)
            ?: throw RuntimeException("Pet not found")

        val vitalSigns = VitalSigns(
            temperature = vitalSignsDTO.temperature,
            heartRate = vitalSignsDTO.heartRate,
            date = vitalSignsDTO.date,
            time = vitalSignsDTO.time,
            device = vitalSignsDTO.device,
            pet = pet
        )
        val savedVitalSigns = vitalSignsRepository.save(vitalSigns)
        return convertToDTO(savedVitalSigns)
    }

    fun getVitalSigns(id: Long): VitalSignsDTO {
        val vitalSigns = vitalSignsRepository.findByIdOrNull(id) ?: throw RuntimeException("Vital signs not found")
        return convertToDTO(vitalSigns)
    }

    fun getAllVitalSigns(): List<VitalSignsDTO> {
        return vitalSignsRepository.findAll().map { convertToDTO(it) }
    }

    fun getVitalSignsByPetAndDate(petId: Long, date: LocalDate): List<VitalSignsDTO> {
        return vitalSignsRepository.findByPetIdAndDate(petId, date).map { convertToDTO(it) }
    }

    fun updateVitalSigns(id: Long, vitalSignsDTO: VitalSignsDTO): VitalSignsDTO {
        val existingVitalSigns = vitalSignsRepository.findByIdOrNull(id) ?: throw RuntimeException("Vital signs not found")
        val pet = petRepository.findByIdOrNull(vitalSignsDTO.petId) ?: throw RuntimeException("Pet not found")

        val updatedVitalSigns = existingVitalSigns.copy(
            temperature = vitalSignsDTO.temperature,
            heartRate = vitalSignsDTO.heartRate,
            date = vitalSignsDTO.date,
            time = vitalSignsDTO.time,
            device = vitalSignsDTO.device,
            pet = pet
        )
        return convertToDTO(vitalSignsRepository.save(updatedVitalSigns))
    }

    fun deleteVitalSigns(id: Long) {
        if (vitalSignsRepository.existsById(id)) {
            vitalSignsRepository.deleteById(id)
        } else {
            throw RuntimeException("Vital signs not found")
        }
    }

    private fun convertToDTO(vitalSigns: VitalSigns): VitalSignsDTO {
        return VitalSignsDTO(
            id = vitalSigns.id,
            temperature = vitalSigns.temperature,
            heartRate = vitalSigns.heartRate,
            date = vitalSigns.date,
            time = vitalSigns.time,
            device = vitalSigns.device,
            petId = vitalSigns.pet.id
        )
    }
}
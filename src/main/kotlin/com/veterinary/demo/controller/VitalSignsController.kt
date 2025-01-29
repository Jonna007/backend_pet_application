package com.veterinary.demo.controller

import com.veterinary.demo.dto.VitalSignsDTO
import com.veterinary.demo.service.VitalSignsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/vital-signs")
class VitalSignsController(private val vitalSignsService: VitalSignsService) {

    @PostMapping
    fun recordVitalSigns(@RequestBody vitalSignsDTO: VitalSignsDTO): ResponseEntity<VitalSignsDTO> {
        val recorded = vitalSignsService.recordVitalSigns(vitalSignsDTO)
        return ResponseEntity(recorded, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getVitalSigns(@PathVariable id: Long): ResponseEntity<VitalSignsDTO> {
        val vitalSigns = vitalSignsService.getVitalSigns(id)
        return ResponseEntity.ok(vitalSigns)
    }

    @GetMapping
    fun getAllVitalSigns(): ResponseEntity<List<VitalSignsDTO>> {
        val allVitalSigns = vitalSignsService.getAllVitalSigns()
        return ResponseEntity.ok(allVitalSigns)
    }

    @GetMapping("/pet/{petId}")
    fun getVitalSignsByPetAndDate(
        @PathVariable petId: Long,
        @RequestParam date: LocalDate
    ): ResponseEntity<List<VitalSignsDTO>> {
        val vitalSigns = vitalSignsService.getVitalSignsByPetAndDate(petId, date)
        return ResponseEntity.ok(vitalSigns)
    }

    @PutMapping("/{id}")
    fun updateVitalSigns(@PathVariable id: Long, @RequestBody vitalSignsDTO: VitalSignsDTO): ResponseEntity<VitalSignsDTO> {
        val updatedVitalSigns = vitalSignsService.updateVitalSigns(id, vitalSignsDTO)
        return ResponseEntity.ok(updatedVitalSigns)
    }

    @DeleteMapping("/{id}")
    fun deleteVitalSigns(@PathVariable id: Long): ResponseEntity<Unit> {
        vitalSignsService.deleteVitalSigns(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
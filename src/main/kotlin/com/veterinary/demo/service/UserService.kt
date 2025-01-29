package com.veterinary.demo.service

import com.veterinary.demo.dto.UserDTO
import com.veterinary.demo.dto.UserResponseDTO
import com.veterinary.demo.model.User
import com.veterinary.demo.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.data.repository.findByIdOrNull

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(userDTO: UserDTO): UserResponseDTO {
        val user = User(
            name = userDTO.name,
            email = userDTO.email,
            password = userDTO.password ?: throw IllegalArgumentException("Password is required")
        )
        val savedUser = userRepository.save(user)
        return convertToResponseDTO(savedUser)
    }

    fun getUser(id: Long): UserResponseDTO {
        val user = userRepository.findByIdOrNull(id) ?: throw RuntimeException("User not found")
        return convertToResponseDTO(user)
    }

    fun getAllUsers(): List<UserResponseDTO> {
        return userRepository.findAll().map { convertToResponseDTO(it) }
    }

    fun updateUser(id: Long, userDTO: UserDTO): UserResponseDTO {
        val existingUser = userRepository.findByIdOrNull(id) ?: throw RuntimeException("User not found")
        val updatedUser = existingUser.copy(
            name = userDTO.name,
            email = userDTO.email,
            password = userDTO.password ?: existingUser.password
        )
        return convertToResponseDTO(userRepository.save(updatedUser))
    }

    fun deleteUser(id: Long) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id)
        } else {
            throw RuntimeException("User not found")
        }
    }

    private fun convertToResponseDTO(user: User): UserResponseDTO {
        return UserResponseDTO(
            id = user.id,
            name = user.name,
            email = user.email,
            registrationDate = user.registrationDate
        )
    }
}
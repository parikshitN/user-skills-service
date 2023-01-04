package com.demo.user.domain.usecase

import com.demo.user.domain.exception.ApiException
import com.demo.user.domain.repository.UserRepository
import com.demo.user.domain.usecase.input.ExpertiseInput
import com.demo.user.domain.usecase.output.UserExpertiseOutput
import java.util.UUID

class UpdateUserExpertise(private val userRepository: UserRepository) {

    operator fun invoke(userId: UUID, expertise: List<ExpertiseInput>): UserExpertiseOutput {
        val user = userRepository.findById(userId) ?: throw ApiException("User doesn't already exists")
        val saved = userRepository.save(user.copy(expertise = expertise.map { it.toExpertise() }))
        return saved.toUserSkillOutput()
    }
}

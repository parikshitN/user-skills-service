package com.demo.user.domain.usecase

import com.demo.user.domain.exception.ApiException
import com.demo.user.domain.repository.UserRepository
import com.demo.user.domain.usecase.input.UserExpertiseInput
import com.demo.user.domain.usecase.output.UserExpertiseOutput

class UpdateUserExpertise(private val userRepository: UserRepository) {

    operator fun invoke(userExpertiseInput: UserExpertiseInput): UserExpertiseOutput {
        val user = userRepository.findById(userExpertiseInput.userId) ?: throw ApiException("User doesn't already exists")
        val saved = userRepository.save(user.copy(skills2 = userExpertiseInput.skills2.map { it.toExpertise() }))
        return saved.toUserSkillOutput()
    }
}

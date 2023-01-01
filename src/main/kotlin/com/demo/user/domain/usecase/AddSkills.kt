package com.demo.user.domain.usecase

import com.demo.user.domain.exception.ApiException
import com.demo.user.domain.repository.UserSkillRepository
import com.demo.user.domain.usecase.input.UserExpertiseInput
import com.demo.user.domain.usecase.output.UserExpertiseOutput

class AddSkills(private val userSkillRepository: UserSkillRepository) {

    operator fun invoke(userExpertiseInput: UserExpertiseInput): UserExpertiseOutput {
        val user = userSkillRepository.findById(userExpertiseInput.userId) ?: throw ApiException("User doesn't already exists")
        val saved = userSkillRepository.save(user.copy(skills2 = userExpertiseInput.skills2.map { it.toExpertise() }))
        return saved.toUserSkillOutput()
    }
}

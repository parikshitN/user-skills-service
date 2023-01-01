package com.demo.user.domain.usecase

import com.demo.user.domain.exception.ApiException
import com.demo.user.domain.repository.UserSkillRepository
import com.demo.user.domain.usecase.input.UserSkillsInput
import com.demo.user.domain.usecase.output.UserSkillsOutput

class AddSkills(private val userSkillRepository: UserSkillRepository) {

    operator fun invoke(userSkillsInput: UserSkillsInput): UserSkillsOutput {
        val user = userSkillRepository.findById(userSkillsInput.userId) ?: throw ApiException("User doesn't already exists")
        val saved = userSkillRepository.save(user.copy(skills = userSkillsInput.skills))
        return saved.toUserSkillOutput()
    }
}

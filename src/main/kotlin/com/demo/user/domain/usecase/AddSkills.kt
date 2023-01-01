package com.demo.user.domain.usecase

import com.demo.user.domain.exception.ApiException
import com.demo.user.domain.model.UserSkills
import com.demo.user.domain.repository.UserSkillRepository
import com.demo.user.domain.usecase.input.UserInput
import com.demo.user.domain.usecase.output.UserSkillsOutput

class AddSkills(private val userSkillRepository: UserSkillRepository) {

    operator fun invoke(userInput: UserInput): UserSkillsOutput {
        userSkillRepository.findById(userInput.userId) ?: throw ApiException("User doesn't already exists")
        val saved = userSkillRepository.save(
            UserSkills(
                userInput.userId,
                userInput.skills,
                "John",
                "Doe",
                "john.doe@test.com"
            )
        )
        return saved.toUserSkillOutput()
    }
}

package com.demo.user.domain.usecase

import com.demo.user.domain.repository.UserSkillRepository
import com.demo.user.domain.usecase.input.UserInput
import com.demo.user.domain.usecase.output.UserSkillsOutput

class CreateUser(private val userSkillRepository: UserSkillRepository) {
    operator fun invoke(userInput: UserInput): UserSkillsOutput {
        val saved = userSkillRepository.save(userInput.toUserSkills())
        return saved.toUserSkillOutput()
    }
}

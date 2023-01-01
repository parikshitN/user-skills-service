package com.demo.user.usecase

import com.demo.user.model.UserSkills
import com.demo.user.repository.UserSkillRepository
import com.demo.user.usecase.input.UserSkillsInput
import com.demo.user.usecase.output.UserSkillsOutput

class AddSkills(private val userSkillRepository: UserSkillRepository) {

    operator fun invoke(userSkillsInput: UserSkillsInput): UserSkillsOutput {
        val saved = userSkillRepository.save(UserSkills(userSkillsInput.userId, userSkillsInput.skills))
        return saved.toUserSkillOutput()
    }
}

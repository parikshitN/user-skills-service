package com.demo.user

import java.util.*

class AddSkill(val userSkillRepository: UserSkillRepository) {

    operator fun invoke(userId: UUID, skills: List<UUID>, userSkillInput: UserSkillInput) : UserSkillOutput {
        val saved = userSkillRepository.save(UserSkill(userSkillInput.userId, userSkillInput.skills))
        return UserSkillOutput(saved.userId, saved.skills)
    }

}

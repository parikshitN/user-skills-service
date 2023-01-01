package com.demo.user.domain.usecase.input

import com.demo.user.domain.usecase.output.UserSkillsOutput
import java.util.UUID

class UserSkillsInput(val skills: List<UUID>, val userId: UUID) {
    fun toUserSkills() = UserSkillsOutput(userId, skills)
}

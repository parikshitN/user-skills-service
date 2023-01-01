package com.demo.user.model

import com.demo.user.usecase.output.UserSkillsOutput
import java.util.UUID

data class UserSkills(val userId: UUID, val skills: List<UUID> = emptyList()) {
    fun toUserSkillOutput() = UserSkillsOutput(userId, skills)
}

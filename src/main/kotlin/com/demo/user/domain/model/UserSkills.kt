package com.demo.user.domain.model

import com.demo.user.domain.usecase.output.UserSkillsOutput
import java.util.UUID

data class UserSkills(val userId: UUID, val skills: List<UUID> = emptyList()) {
    fun toUserSkillOutput() = UserSkillsOutput(userId, skills)
}

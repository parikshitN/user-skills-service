package com.demo.user.domain.model

import com.demo.user.domain.usecase.output.UserOutput
import com.demo.user.domain.usecase.output.UserSkillsOutput
import java.util.UUID

data class UserSkills(
    val userId: UUID,
    val skills: List<UUID> = emptyList(),
    val firstName: String,
    val lastName: String,
    val email: String
) {
    fun toUserSkillOutput() = UserSkillsOutput(userId, skills)
    fun toUserOutput(): UserOutput {
        return UserOutput(userId, firstName, lastName, email, skills)
    }
}

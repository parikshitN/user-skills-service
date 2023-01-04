package com.demo.user.domain.model

import com.demo.user.domain.usecase.output.UserExpertiseOutput
import com.demo.user.domain.usecase.output.UserOutput
import java.util.UUID

data class User(
    val userId: UUID = UUID.randomUUID(),
    val firstName: String,
    val lastName: String,
    val email: String,
    val expertise: List<Expertise>
) {
    fun toUserSkillOutput() = UserExpertiseOutput(
        userId,
        expertise.map { it.toExpertiseOutput() }
    )
    fun toUserOutput(): UserOutput {
        return UserOutput(userId, firstName, lastName, email, expertise.map { it.toExpertiseOutput() })
    }
}

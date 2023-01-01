package com.demo.user.domain.model

import com.demo.user.domain.usecase.output.UserExpertiseOutput
import com.demo.user.domain.usecase.output.UserOutput
import java.util.UUID

data class User(
    val userId: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val skills2: List<Expertise>
) {
    fun toUserSkillOutput() = UserExpertiseOutput(
        userId,
        skills2.map { it.toExpertiseOutput() })
    fun toUserOutput(): UserOutput {
        return UserOutput(userId, firstName, lastName, email)
    }
}

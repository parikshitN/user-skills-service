package com.demo.user.domain.usecase.input

import com.demo.user.domain.model.UserSkills
import java.util.UUID

class UserInput(val skills: List<UUID>, val userId: UUID, val firstName: String, val lastName: String, email: String) {
    fun toUserSkills() = UserSkills(userId, skills, "John", "Doe", "john.doe@test.com")
}

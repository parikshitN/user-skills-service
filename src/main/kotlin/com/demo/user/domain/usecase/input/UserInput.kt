package com.demo.user.domain.usecase.input

import com.demo.user.domain.model.User
import java.util.UUID

data class UserInput(val userId: UUID, val firstName: String, val lastName: String, val email: String) {
    fun toUserSkills() = User(userId, firstName, lastName, email, listOf())
}

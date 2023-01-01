package com.demo.user.domain.usecase.input

import com.demo.user.domain.model.User

data class UserInput(val firstName: String, val lastName: String, val email: String) {
    fun toUser() = User(firstName = firstName, lastName = lastName, email = email, skills2 = listOf())
}

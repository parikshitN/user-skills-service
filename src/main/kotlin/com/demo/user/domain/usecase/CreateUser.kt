package com.demo.user.domain.usecase

import com.demo.user.domain.repository.UserRepository
import com.demo.user.domain.usecase.input.UserInput
import com.demo.user.domain.usecase.output.UserOutput

class CreateUser(private val userRepository: UserRepository) {
    operator fun invoke(userInput: UserInput): UserOutput {
        val saved = userRepository.save(userInput.toUser())
        return saved.toUserOutput()
    }
}

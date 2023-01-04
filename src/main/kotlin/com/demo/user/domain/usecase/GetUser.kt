package com.demo.user.domain.usecase

import com.demo.user.domain.exception.ApiException
import com.demo.user.domain.repository.UserRepository
import com.demo.user.domain.usecase.output.UserOutput
import java.util.UUID

class GetUser(private val userRepository: UserRepository) {
    operator fun invoke(userId: UUID): UserOutput {
        val user = userRepository.findById(userId) ?: throw ApiException("User not found")
        return user.toUserOutput()
    }
}

package com.demo.user.domain.usecase

import com.demo.user.domain.repository.UserRepository
import java.util.UUID

class RemoveExpertise(private val userRepository: UserRepository) {
    operator fun invoke(skillId: UUID) {
        userRepository.deleteExpertiseForSkill(skillId)
    }
}

package com.demo.user.domain.usecase

import com.demo.user.domain.repository.UserRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.util.UUID

class RemoveExpertiseTest {

    @Test
    fun `should remove expertise for a given skill`() {
        val userRepository = mockk<UserRepository>(relaxed = true)
        val removeExpertise = RemoveExpertise(userRepository)
        val skillId = UUID.randomUUID()

        removeExpertise(skillId)

        verify(exactly = 1) { userRepository.deleteExpertiseForSkill(skillId) }
    }
}

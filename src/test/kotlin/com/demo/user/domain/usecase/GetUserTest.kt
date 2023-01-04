package com.demo.user.domain.usecase

import com.demo.user.domain.exception.ApiException
import com.demo.user.domain.model.Expertise
import com.demo.user.domain.model.SkillLevel
import com.demo.user.domain.model.User
import com.demo.user.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.UUID

class GetUserTest {
    private val userRepository = mockk<UserRepository>(relaxed = true)
    private val getUser = GetUser(userRepository)

    @Test
    fun `should get the user for a given userId`() {
        val userId = UUID.randomUUID()
        val expertise1 = Expertise(UUID.randomUUID(), SkillLevel.BASIC, experience = 5)
        val expertise2 = Expertise(UUID.randomUUID(), SkillLevel.INTERMEDIATE, experience = 7)
        val user = User(
            userId, "Rob", "Stark", "rob@test.com", listOf(expertise1, expertise2)
        )
        every { userRepository.findById(userId) } returns user

        val actual = getUser(userId)

        actual `should be equal to` user.toUserOutput()
    }

    @Test
    fun `should throw exception if user doesn't exist`() {
        every { userRepository.findById(any()) } returns null

        val error = Assertions.assertThrows(ApiException::class.java) { getUser(UUID.randomUUID()) }

        error.message `should be equal to` "User not found"
    }
}

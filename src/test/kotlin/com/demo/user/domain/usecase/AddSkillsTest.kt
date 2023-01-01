package com.demo.user.domain.usecase

import com.demo.user.domain.exception.ApiException
import com.demo.user.domain.model.UserSkills
import com.demo.user.domain.repository.UserSkillRepository
import com.demo.user.domain.usecase.input.UserInput
import com.demo.user.domain.usecase.output.UserSkillsOutput
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.UUID

class AddSkillsTest {

    @Test
    fun `user should be able to add a skill to self`() {
        val userSkillRepository = mockk<UserSkillRepository>(relaxed = true)
        val userId = UUID.randomUUID()
        every { userSkillRepository.findById(userId) } returns UserSkills(
            userId = userId,
            skills = emptyList(),
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@test.com"
        )
        val skillId = UUID.randomUUID()
        every {
            userSkillRepository.save(
                UserSkills(
                    userId = userId,
                    skills = listOf(skillId),
                    firstName = "John",
                    lastName = "Doe",
                    email = "john.doe@test.com"
                )
            )
        } returns UserSkills(
            userId = userId,
            skills = listOf(skillId),
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@test.com"
        )
        val usecase = AddSkills(userSkillRepository)

        val output = usecase(
            UserInput(
                skills = listOf(skillId),
                userId = userId,
                firstName = "Jhon",
                lastName = "Doe",
                email = "john.doe@test.com"
            )
        )

        val expected = UserSkillsOutput(userId = userId, skills = listOf(skillId))
        output `should be equal to` expected
    }

    @Test
    fun `should throw error if user doesnt exists`() {
        val userSkillRepository = mockk<UserSkillRepository>(relaxed = true)
        val userId = UUID.randomUUID()
        every { userSkillRepository.findById(userId) } returns null
        val usecase = AddSkills(userSkillRepository)

        val error = Assertions.assertThrows(ApiException::class.java) {
            usecase(
                UserInput(
                    skills = listOf(
                        UUID.randomUUID()
                    ),
                    userId = userId,
                    firstName = "Jhon",
                    lastName = "Doe",
                    email = "john.doe@test.com"
                )
            )
        }

        error.message `should be equal to` "User doesn't already exists"
    }
}

package com.demo.user.domain.usecase

import com.demo.user.domain.exception.ApiException
import com.demo.user.domain.model.UserSkills
import com.demo.user.domain.repository.UserSkillRepository
import com.demo.user.domain.usecase.input.UserSkillsInput
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
            firstName = "Mark",
            lastName = "Ryan",
            email = "mark.ryan@test.com"
        )
        val skillId = UUID.randomUUID()
        every {
            userSkillRepository.save(
                UserSkills(
                    userId = userId,
                    skills = listOf(skillId),
                    firstName = "Mark",
                    lastName = "Ryan",
                    email = "mark.ryan@test.com"
                )
            )
        } returns UserSkills(
            userId = userId,
            skills = listOf(skillId),
            firstName = "Mark",
            lastName = "Ryan",
            email = "mark.ryan@test.com"
        )
        val usecase = AddSkills(userSkillRepository)

        val output = usecase(
            UserSkillsInput(
                skills = listOf(skillId),
                userId = userId
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
                UserSkillsInput(
                    skills = listOf(
                        UUID.randomUUID()
                    ),
                    userId = userId,
                )
            )
        }

        error.message `should be equal to` "User doesn't already exists"
    }
}

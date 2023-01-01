package com.demo.user.domain.usecase

import com.demo.user.domain.exception.ApiException
import com.demo.user.domain.model.Expertise
import com.demo.user.domain.model.SkillLevel
import com.demo.user.domain.model.User
import com.demo.user.domain.repository.UserSkillRepository
import com.demo.user.domain.usecase.input.ExpertiseInput
import com.demo.user.domain.usecase.input.UserExpertiseInput
import com.demo.user.domain.usecase.output.ExpertiseOutput
import com.demo.user.domain.usecase.output.UserExpertiseOutput
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
        val skillId = UUID.randomUUID()
        val skill = Expertise(skillId = skillId, level = SkillLevel.BASIC, experience = 3)
        every { userSkillRepository.findById(userId) } returns User(
            userId = userId,
            firstName = "Mark",
            lastName = "Ryan",
            email = "mark.ryan@test.com",
            skills2 = listOf(skill)
        )
        every {
            userSkillRepository.save(
                User(
                    userId = userId,
                    firstName = "Mark",
                    lastName = "Ryan",
                    email = "mark.ryan@test.com",
                    skills2 = listOf(skill)
                )
            )
        } returns User(
            userId = userId,
            firstName = "Mark",
            lastName = "Ryan",
            email = "mark.ryan@test.com",
            skills2 = listOf(skill)
        )
        val usecase = AddSkills(userSkillRepository)

        val output = usecase(UserExpertiseInput(
            userId = userId,
            skills2 = listOf(ExpertiseInput(skillId = skillId, level = "Basic", experience = 3))
        ))

        val expected = UserExpertiseOutput(
            userId = userId,
            skills2 = listOf(ExpertiseOutput(skillId = skillId, level = "Basic", experience = 3))
        )
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
                UserExpertiseInput(
                    userId = userId,
                    skills2 = listOf(ExpertiseInput(skillId = UUID.randomUUID(), level = "Basic", experience = 3)),
                )
            )
        }

        error.message `should be equal to` "User doesn't already exists"
    }
}

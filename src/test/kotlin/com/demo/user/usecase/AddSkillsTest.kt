package com.demo.user.usecase

import com.demo.user.repository.UserSkillRepository
import com.demo.user.model.UserSkills
import com.demo.user.usecase.input.UserSkillsInput
import com.demo.user.usecase.output.UserSkillsOutput
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import java.util.*

class AddSkillsTest {

    @Test
    fun `user should be able to add a skill to self`() {
        val userSkillRepository = mockk<UserSkillRepository>(relaxed = true)
        val userId = UUID.randomUUID()
        every { userSkillRepository.findById(userId) } returns UserSkills(userId = userId, skills = emptyList())
        val skillId = UUID.randomUUID()
        every { userSkillRepository.save(UserSkills(userId = userId, skills = listOf(skillId))) } returns UserSkills(
            userId = userId,
            skills = listOf(skillId)
        )
        val usecase = AddSkills(userSkillRepository)

        val output = usecase(UserSkillsInput(userId = userId, skills = listOf(skillId)))

        val expected = UserSkillsOutput(userId = userId, skills = listOf(skillId))
        output `should be equal to` expected
    }
}

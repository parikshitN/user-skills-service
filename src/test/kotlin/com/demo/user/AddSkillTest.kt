package com.demo.user

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import java.util.UUID

class AddSkillTest {

    @Test
    fun `user should be able to add a skill to self`() {
        val userSkillRepository = mockk<UserSkillRepository>(relaxed = true)
        val userId = UUID.randomUUID()
        every { userSkillRepository.findById(userId) } returns UserSkill(userId = userId, skills = emptyList())
        val skillId = UUID.randomUUID()
        every { userSkillRepository.save(UserSkill(userId = userId, skills = listOf(skillId)))} returns UserSkill(userId = userId, skills = listOf(skillId))
        val usecase = AddSkill(userSkillRepository)

        val output = usecase(userId = userId, skills = listOf(skillId), UserSkillInput(userId = userId, skills = listOf(skillId)))

        val expected = UserSkillOutput(userId = userId, skills = listOf(skillId))
        output `should be equal to` expected
    }
}

package com.demo.user.domain.usecase

import com.demo.user.domain.model.UserSkills
import com.demo.user.domain.repository.UserSkillRepository
import com.demo.user.domain.usecase.input.UserInput
import com.demo.user.domain.usecase.output.UserSkillsOutput
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import java.util.UUID

class CreateUserTest {

    @Test
    fun `should be able to add a new user`() {
        val userSkillRepository = mockk<UserSkillRepository>(relaxed = true)
        val userId = UUID.randomUUID()
        val userSkills = UserSkills(userId = userId, firstName = "John", lastName = "Doe", email = "john.doe@test.com", skills = emptyList())
        every {
            userSkillRepository.save(userSkills)
        } returns userSkills
        val createUser = CreateUser(userSkillRepository)

        val output = createUser(UserInput(userId = userId, firstName = "John", lastName = "Doe", email = "john.doe@test.com", skills = emptyList()))

        val expected = UserSkillsOutput(userId = userId, skills = emptyList())
        output `should be equal to` expected
    }
}

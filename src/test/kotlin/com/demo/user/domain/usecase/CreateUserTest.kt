package com.demo.user.domain.usecase

import com.demo.user.domain.model.User
import com.demo.user.domain.repository.UserRepository
import com.demo.user.domain.usecase.input.UserInput
import com.demo.user.domain.usecase.output.UserOutput
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import java.util.UUID

class CreateUserTest {

    @Test
    fun `should be able to add a new user`() {
        val userRepository = mockk<UserRepository>(relaxed = true)
        val userId = UUID.randomUUID()
        val user = User(
            userId = userId,
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@test.com",
            expertise = listOf()
        )
        every {
            userRepository.save(any())
        } returns user
        val createUser = CreateUser(userRepository)

        val output = createUser(
            UserInput(
                firstName = "John",
                lastName = "Doe",
                email = "john.doe@test.com"
            )
        )

        val userArg = slot<User>()
        verify(exactly = 1) { userRepository.save(capture(userArg)) }
        userArg.captured.firstName `should be equal to` "John"
        userArg.captured.lastName `should be equal to` "Doe"
        userArg.captured.email `should be equal to` "john.doe@test.com"
        val expected = UserOutput(
            userId = userId,
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@test.com",
            expertise = emptyList()
        )
        output `should be equal to` expected
    }
}

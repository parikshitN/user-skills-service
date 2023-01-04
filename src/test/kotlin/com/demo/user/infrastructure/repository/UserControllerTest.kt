package com.demo.user.infrastructure.repository

import com.demo.user.domain.model.Expertise
import com.demo.user.domain.model.SkillLevel
import com.demo.user.domain.model.User
import com.demo.user.domain.repository.UserRepository
import com.demo.user.domain.usecase.input.ExpertiseInput
import com.demo.user.domain.usecase.input.UserInput
import com.demo.user.domain.usecase.output.UserExpertiseOutput
import com.demo.user.domain.usecase.output.UserOutput
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.test.context.ContextConfiguration
import java.util.UUID

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [MongoDBContainerInitializer::class])
class UserControllerTest {

    @Value(value = "\${local.server.port}")
    private val port = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        restTemplate.restTemplate.requestFactory = HttpComponentsClientHttpRequestFactory()
    }

    @Test
    fun `should create user`() {
        val input = UserInput("John", "Snow", "john@test.com")

        val response = restTemplate.postForEntity("http://localhost:$port/api/users", input, UserOutput::class.java)

        response.statusCode `should be equal to` HttpStatus.OK
        val userOutput = response.body
        userOutput?.firstName `should be equal to` "John"
        userOutput?.lastName `should be equal to` "Snow"
        userOutput?.email `should be equal to` "john@test.com"
    }

    @Test
    fun `should add an expertise for a user`() {
        val user = User(UUID.randomUUID(), "John", "Snow", "john@test.com", emptyList())
        userRepository.save(user)
        val expertiseInput = ExpertiseInput(
            UUID.randomUUID(), SkillLevel.BASIC.label, 5
        )

        val response = restTemplate.patchForObject(
            "http://localhost:$port/api/users/${user.userId}/expertise",
            listOf(expertiseInput),
            UserExpertiseOutput::class.java
        )

        response.userId `should be equal to` user.userId
        response.expertise[0].skillId `should be equal to` expertiseInput.skillId
        response.expertise[0].level `should be equal to` expertiseInput.level
        response.expertise[0].experience `should be equal to` expertiseInput.experience
    }

    @Test
    fun `should get a user with all its details`() {
        val userId = UUID.randomUUID()
        val expertise1 = Expertise(UUID.randomUUID(), SkillLevel.BASIC, 4)
        val expertise2 = Expertise(UUID.randomUUID(), SkillLevel.INTERMEDIATE, 6)
        val user = User(
            userId, "John", "Snow", "john@test.com", listOf(expertise1, expertise2)
        )
        userRepository.save(user)

        val response = restTemplate.getForEntity("http://localhost:$port/api/users/$userId", UserOutput::class.java)

        response.statusCode `should be equal to` HttpStatus.OK
        val userOutput = response.body
        userOutput?.firstName `should be equal to` "John"
        userOutput?.lastName `should be equal to` "Snow"
        userOutput?.email `should be equal to` "john@test.com"
        userOutput?.expertise `should be equal to` listOf(expertise1.toExpertiseOutput(), expertise2.toExpertiseOutput())
    }
}

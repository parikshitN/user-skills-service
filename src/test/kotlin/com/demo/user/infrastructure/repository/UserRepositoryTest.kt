package com.demo.user.infrastructure.repository

import com.demo.user.domain.model.Expertise
import com.demo.user.domain.model.SkillLevel
import com.demo.user.domain.model.User
import com.demo.user.domain.repository.UserRepository
import com.demo.user.infrastructure.repository.entities.ExpertiseDocument
import com.demo.user.infrastructure.repository.entities.UserDocument
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ContextConfiguration
import java.util.UUID

@SpringBootTest
@ContextConfiguration(initializers = [MongoDBContainerInitializer::class])
class UserRepositoryTest(@Autowired private val userRepository: UserRepository,
                         @Autowired private val mongoTemplate: MongoTemplate
) {

    @Test
    fun `should save new User`() {
        val saved = userRepository.save(User(firstName = "Tyrion", lastName = "Lanister", email = "tyrion@test.com", skills2 = emptyList()))

        val document = mongoTemplate.findById(saved.userId, UserDocument::class.java)

        document?.uuid `should be equal to`  saved.userId
        document?.firstName `should be equal to` saved.firstName
        document?.lastName `should be equal to` saved.lastName
        document?.email `should be equal to` saved.email
        document?.skills `should be equal to` saved.skills2
    }

    @Disabled//todo: unique index not working - need to fix
    @Test
    fun `should not save user if a user already exists with same email`() {
        val user = User(firstName = "Tyrion", lastName = "Lanister", email = "tyrion@test.com", skills2 = emptyList())
        mongoTemplate.save(UserDocument.from(user))

        Assertions.assertThrows(java.lang.Exception::class.java){
            userRepository.save(user.copy(userId = UUID.randomUUID(), firstName = "Jamie", lastName = "Lanister"))
        }
    }

    @Test
    fun `should save expertise for a user`() {
        val expertise = Expertise(UUID.randomUUID(), SkillLevel.BASIC, 3)
        val user = User(firstName = "Tyrion", lastName = "Lanister", email = "tyrion@test.com", skills2 = listOf(
            expertise
        ))

        val saved = userRepository.save(user)

        val document  = mongoTemplate.findById(saved.userId, UserDocument::class.java)
        document?.skills `should be equal to` listOf(ExpertiseDocument.from(expertise))
    }
}

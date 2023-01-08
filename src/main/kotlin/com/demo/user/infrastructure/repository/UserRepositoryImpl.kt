package com.demo.user.infrastructure.repository

import com.demo.user.domain.model.User
import com.demo.user.domain.repository.UserRepository
import com.demo.user.infrastructure.repository.entities.UserDocument
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class UserRepositoryImpl(private val userMongoRepository: UserMongoRepository) : UserRepository {

    override fun findById(userId: UUID): User? {
        return userMongoRepository.findById(userId).orElse(null)?.toUser()
    }

    override fun save(user: User): User {
        return userMongoRepository.save(UserDocument.from(user)).toUser()
    }

    override fun deleteExpertiseForSkill(skillId1: UUID) {
        userMongoRepository.deleteExpertiseForSkill(skillId1)
    }
}

interface CustomUserRepository {
    fun deleteExpertiseForSkill(skillId: UUID)
}

class CustomUserRepositoryImpl(private val mongoTemplate: MongoTemplate) : CustomUserRepository {
    override fun deleteExpertiseForSkill(skillId: UUID) {
        val update = Update()
        update.pull("skills", Query(Criteria.where("skillId").isEqualTo(skillId)))
        mongoTemplate.updateMulti(Query(), update, UserDocument::class.java)
    }
}

@Repository
interface UserMongoRepository : MongoRepository<UserDocument, UUID>, CustomUserRepository

package com.demo.user.infrastructure.repository

import com.demo.user.domain.model.User
import com.demo.user.domain.repository.UserRepository
import com.demo.user.infrastructure.repository.entities.UserDocument
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
}

@Repository
interface UserMongoRepository : MongoRepository<UserDocument, UUID>

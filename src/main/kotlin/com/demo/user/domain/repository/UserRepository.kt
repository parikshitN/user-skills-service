package com.demo.user.domain.repository

import com.demo.user.domain.model.User
import java.util.UUID

interface UserRepository {
    fun findById(userId: UUID): User?

    fun save(user: User): User

    fun deleteExpertiseForSkill(skillId1: UUID)
}

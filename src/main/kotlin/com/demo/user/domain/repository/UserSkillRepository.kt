package com.demo.user.domain.repository

import com.demo.user.domain.model.User
import java.util.UUID

interface UserSkillRepository {
    fun findById(userId: UUID): User?

    fun save(user: User): User
}

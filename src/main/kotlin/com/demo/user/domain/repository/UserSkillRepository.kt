package com.demo.user.domain.repository

import com.demo.user.domain.model.UserSkills
import java.util.UUID

interface UserSkillRepository {
    fun findById(userId: UUID): UserSkills?

    fun save(userSkills: UserSkills): UserSkills
}

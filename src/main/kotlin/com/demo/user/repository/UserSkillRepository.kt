package com.demo.user.repository

import com.demo.user.model.UserSkills
import java.util.*

interface UserSkillRepository {
    fun findById(userId: UUID) : UserSkills

    fun save(userSkills: UserSkills) : UserSkills
}

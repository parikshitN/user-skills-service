package com.demo.user.usecase.input

import com.demo.user.usecase.output.UserSkillsOutput
import java.util.*

class UserSkillsInput(val skills: List<UUID>, val userId: UUID) {
    fun toUserSkills() = UserSkillsOutput(userId, skills)

}

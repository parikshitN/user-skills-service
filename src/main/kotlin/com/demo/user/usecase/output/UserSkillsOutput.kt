package com.demo.user.usecase.output

import java.util.*

data class UserSkillsOutput(val userId: UUID, val skills: List<UUID>)

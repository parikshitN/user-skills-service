package com.demo.user.domain.usecase.input

import java.util.UUID

data class UserSkillsInput(val userId: UUID, val skills: List<UUID>)

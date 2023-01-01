package com.demo.user.domain.usecase.output

import java.util.UUID

data class UserSkillsOutput(val userId: UUID, val skills: List<UUID>)

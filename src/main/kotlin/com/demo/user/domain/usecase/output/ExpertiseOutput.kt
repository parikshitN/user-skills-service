package com.demo.user.domain.usecase.output

import java.util.UUID

data class ExpertiseOutput(val skillId: UUID, val level: String, val experience: Int)

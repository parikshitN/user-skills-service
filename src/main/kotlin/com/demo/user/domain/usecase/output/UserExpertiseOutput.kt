package com.demo.user.domain.usecase.output

import java.util.UUID

data class UserExpertiseOutput(val userId: UUID, val expertise: List<ExpertiseOutput>)

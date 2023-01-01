package com.demo.user.domain.usecase.input

import java.util.UUID

data class UserExpertiseInput(val userId: UUID, val skills2: List<ExpertiseInput>)

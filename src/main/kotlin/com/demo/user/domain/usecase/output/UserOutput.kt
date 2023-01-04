package com.demo.user.domain.usecase.output

import java.util.UUID

data class UserOutput(
    val userId: UUID,
    val firstName: String,
    val lastName: String,
    val email: String,
    val expertise: List<ExpertiseOutput>
)

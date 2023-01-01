package com.demo.user

import java.util.*

data class UserSkill(val userId: UUID, val skills: List<UUID> = emptyList()) {

}

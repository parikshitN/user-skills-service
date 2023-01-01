package com.demo.user.domain.usecase.input

import com.demo.user.domain.model.Expertise
import com.demo.user.domain.model.SkillLevel
import java.util.UUID

data class ExpertiseInput(val skillId: UUID, val level: String, val experience: Int) {
    fun toExpertise(): Expertise {
        return Expertise(skillId, SkillLevel.from(level), experience)
    }
}

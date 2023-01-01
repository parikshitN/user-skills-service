package com.demo.user.domain.model

import com.demo.user.domain.usecase.output.ExpertiseOutput
import java.util.UUID


data class Expertise(val skillId: UUID, val level: SkillLevel, val experience: Int) {
    fun toExpertiseOutput() : ExpertiseOutput{
        return ExpertiseOutput(skillId, level.label, experience)
    }
}

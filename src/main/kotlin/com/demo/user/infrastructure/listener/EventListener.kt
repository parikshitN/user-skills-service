package com.demo.user.infrastructure.listener

import com.demo.skills.domain.event.SkillDeletedEvent
import com.demo.user.domain.usecase.RemoveExpertise
import java.util.UUID

class EventListener(val removeExpertise: RemoveExpertise) {

    fun process(skillDeletedEvent: SkillDeletedEvent) {
        println(skillDeletedEvent)
        removeExpertise(UUID.fromString(skillDeletedEvent.uuid))
    }
}

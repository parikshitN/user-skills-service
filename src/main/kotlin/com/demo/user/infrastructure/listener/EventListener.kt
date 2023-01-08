package com.demo.user.infrastructure.listener

import com.demo.skills.domain.event.SkillDeletedEvent

class EventListener {

    fun process(skillDeletedEvent: SkillDeletedEvent) {
        println(skillDeletedEvent)
    }
}

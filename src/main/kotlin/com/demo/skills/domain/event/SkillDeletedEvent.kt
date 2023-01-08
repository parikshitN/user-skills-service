package com.demo.skills.domain.event

// todo: change the package, currently it is failing in deserialization if package changed
data class SkillDeletedEvent(val uuid: String) {
    constructor() : this("") {
    }
}

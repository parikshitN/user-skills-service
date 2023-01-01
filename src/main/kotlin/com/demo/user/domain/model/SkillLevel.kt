package com.demo.user.domain.model

import com.demo.user.domain.exception.ApiException

enum class SkillLevel(val label: String) {
    BASIC("Basic"), INTERMEDIATE("Intermediate"), EXPERT("Expert");

    companion object {
        fun from(label: String): SkillLevel {
            return values().find { it.label == label } ?: throw ApiException("Skill level $label doesn't exists")
        }
    }
}

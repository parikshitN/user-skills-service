package com.demo.user.infrastructure.repository.entities

import com.demo.user.domain.model.Expertise
import com.demo.user.domain.model.SkillLevel
import com.demo.user.domain.model.User
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("users")
data class UserDocument(
    @Id val uuid: UUID,
    val firstName: String,
    val lastName: String,
    @Indexed(unique = true) val email: String,
    val skills: List<ExpertiseDocument>
) {
    fun toUser(): User {
        return User(uuid, firstName, lastName, email, skills.map { it.toExpertise() })
    }

    companion object {
        fun from(user: User): UserDocument {
            return UserDocument(user.userId, user.firstName, user.lastName, user.email, user.skills2.map { ExpertiseDocument.from(it) })
        }
    }
}

data class ExpertiseDocument(val skillId: UUID, val level: String, val experience: Int) {
    fun toExpertise(): Expertise {
        return Expertise(skillId, SkillLevel.from(level), experience)
    }

    companion object {
        fun from(expertise: Expertise): ExpertiseDocument {
            return ExpertiseDocument(expertise.skillId, expertise.level.label, expertise.experience)
        }
    }
}

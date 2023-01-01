package com.demo.user

import com.demo.user.domain.repository.UserRepository
import com.demo.user.domain.usecase.CreateUser
import com.demo.user.domain.usecase.UpdateUserExpertise
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
class UserSkillsServiceApplication

fun main(args: Array<String>) {
    runApplication<UserSkillsServiceApplication>(*args)
}

@Configuration
@EnableMongoRepositories
class ApplicationConfiguration {

    @Bean
    fun createUser(userRepository: UserRepository): CreateUser {
        return CreateUser(userRepository)
    }

    @Bean
    fun updateUserExpertise(userRepository: UserRepository): UpdateUserExpertise {
        return UpdateUserExpertise(userRepository)
    }
}

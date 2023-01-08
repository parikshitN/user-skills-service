package com.demo.user

import com.demo.user.domain.repository.UserRepository
import com.demo.user.domain.usecase.CreateUser
import com.demo.user.domain.usecase.GetUser
import com.demo.user.domain.usecase.RemoveExpertise
import com.demo.user.domain.usecase.UpdateUserExpertise
import com.demo.user.infrastructure.listener.EventListener
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
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
    fun getUser(userRepository: UserRepository): GetUser {
        return GetUser(userRepository)
    }

    @Bean
    fun updateUserExpertise(userRepository: UserRepository): UpdateUserExpertise {
        return UpdateUserExpertise(userRepository)
    }

    @Bean
    fun container(
        connectionFactory: ConnectionFactory,
        listenerAdapter: MessageListenerAdapter
    ): SimpleMessageListenerContainer? {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames("skill-management")
        container.setMessageListener(listenerAdapter)
        return container
    }

    @Bean
    fun messageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun listenerAdapter(eventListener: EventListener, messageConverter: MessageConverter): MessageListenerAdapter {
        val messageListenerAdapter = MessageListenerAdapter(eventListener, "process")
        messageListenerAdapter.setMessageConverter(messageConverter)
        return messageListenerAdapter
    }

    @Bean
    fun eventListener(removeExpertise: RemoveExpertise): EventListener {
        return EventListener(removeExpertise)
    }

    @Bean
    fun removeExpertise(userRepository: UserRepository): RemoveExpertise {
        return RemoveExpertise(userRepository)
    }
}

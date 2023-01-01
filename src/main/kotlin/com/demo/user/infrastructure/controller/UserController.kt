package com.demo.user.infrastructure.controller

import com.demo.user.domain.usecase.CreateUser
import com.demo.user.domain.usecase.UpdateUserExpertise
import com.demo.user.domain.usecase.input.UserExpertiseInput
import com.demo.user.domain.usecase.input.UserInput
import com.demo.user.domain.usecase.output.UserExpertiseOutput
import com.demo.user.domain.usecase.output.UserOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired lateinit var createUser: CreateUser
    @Autowired lateinit var updateUserExpertise: UpdateUserExpertise

    @PostMapping
    fun create(@RequestBody userInput: UserInput): UserOutput {
        return createUser(userInput)
    }

    @PatchMapping("/{userId}/expertise")
    fun updateExpertise(@RequestBody userExpertiseInput: UserExpertiseInput): UserExpertiseOutput {
        return updateUserExpertise(userExpertiseInput)
    }
}

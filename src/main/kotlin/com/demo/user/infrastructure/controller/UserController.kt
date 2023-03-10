package com.demo.user.infrastructure.controller

import com.demo.user.domain.usecase.CreateUser
import com.demo.user.domain.usecase.GetUser
import com.demo.user.domain.usecase.UpdateUserExpertise
import com.demo.user.domain.usecase.input.ExpertiseInput
import com.demo.user.domain.usecase.input.UserInput
import com.demo.user.domain.usecase.output.UserExpertiseOutput
import com.demo.user.domain.usecase.output.UserOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired lateinit var createUser: CreateUser
    @Autowired lateinit var getUser: GetUser
    @Autowired lateinit var updateUserExpertise: UpdateUserExpertise

    @PostMapping
    fun create(@RequestBody userInput: UserInput): UserOutput {
        return createUser(userInput)
    }

    @PatchMapping("/{userId}/expertise")
    fun updateExpertise(@PathVariable userId: UUID, @RequestBody expertise: List<ExpertiseInput>): UserExpertiseOutput {
        return updateUserExpertise(userId, expertise)
    }

    @GetMapping("/{userId}")
    fun getUserWith(@PathVariable userId: UUID): UserOutput {
        return getUser(userId)
    }
}

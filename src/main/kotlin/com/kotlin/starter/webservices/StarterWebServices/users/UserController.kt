package com.kotlin.starter.webservices.StarterWebServices.users

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/users")
class UserController(private val userRepository: UserRepository){
    @GetMapping
    fun getAllUsers():List<User> = userRepository.findAll()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable(value = "id") userId:String):ResponseEntity<User>{
        return userRepository.findById(userId)
                .map { user -> ResponseEntity.ok(user) }
                .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createNewUser(@Valid @RequestBody user: User): User = userRepository.save(user)

    @PutMapping("/{id}")
    fun updateUser(@PathVariable(value = "id") userId: String,
                   @Valid @RequestBody newUser: User): ResponseEntity<User>{
        return userRepository.findById(userId).map { existingUser ->
            val updatedUser: User = existingUser
                    .copy(name = newUser.name, password = newUser.password)
            ResponseEntity.ok().body(userRepository.save(updatedUser))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable(value = "id") userId: String):ResponseEntity<Void>{
        return userRepository.findById(userId).map { user ->
            userRepository.delete(user)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}
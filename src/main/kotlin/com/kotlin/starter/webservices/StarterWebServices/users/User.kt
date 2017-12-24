package com.kotlin.starter.webservices.StarterWebServices.users


import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "user", schema = "public")
data class User(
    @Id
    val id: String = UUID.randomUUID().toString(),

    @get: NotBlank
    val name: String = "",

    @get: NotBlank
    val password: String = ""
)
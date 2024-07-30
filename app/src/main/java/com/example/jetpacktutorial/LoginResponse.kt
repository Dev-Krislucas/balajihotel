package com.example.jetpacktutorial

data class LoginResponse(
    val token: String,
    val user: ResponseUser
)
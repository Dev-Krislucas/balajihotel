package com.example.jetpacktutorial

import org.bson.types.ObjectId

data class User(
    val name:String,
    val email:String,
    val password : String,
    val city :String,
    val number : String
)

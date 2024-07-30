package com.example.jetpacktutorial

import org.bson.types.ObjectId
data class ResponseUser(
    val name:String,
    val email:String,
    val password : String,
    val city :String,
    val number : String,
    val cart : List<String>,
    val _id : String


    )

data class SignupResponse(
    val newUser:ResponseUser,
    val token : String,
    val status : Boolean,
    val message : String,
)


package com.example.jetpacktutorial


data class Vehicle(
    val name:String,
    val email:String,
    val password : String,
    val city :String,
    val number : String
)
data class ApiResponse(
    val message:String,
    val allUsers : List<Vehicle>

)
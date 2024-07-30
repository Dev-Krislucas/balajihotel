package com.example.jetpacktutorial

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//In this section we are configuring retrofit ... by making only one instance of it
//object RetrofitInstance{
//    private val BASE_URL : String = "http://localhost:5000"
//    val api: ApiService by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//
//
//}
val retrofit = Retrofit.Builder()
    .baseUrl("https://fivethsembackend-1.onrender.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)
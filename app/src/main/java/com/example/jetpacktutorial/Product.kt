package com.example.jetpacktutorial
import org.bson.types.ObjectId



data class Product(
    val _id : String,
    val name: String,
    val image : List<String>,
    val price: String,

    val category: String,
    val description: String



)

data class ProductResponse(
    val message: String,
    val products : List<Product>


)
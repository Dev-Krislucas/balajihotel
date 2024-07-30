package com.example.jetpacktutorial

import android.telecom.Call
import org.bson.types.ObjectId
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/user")
     suspend fun getVehicles() : ApiResponse
     @POST("/user/signup")
     suspend fun signup( @Body user: User ) : SignupResponse
     @POST("/user/login")
     suspend fun login(@Body request : LoginRequest):LoginResponse



     @GET("/product/get/women")
     suspend fun getWomenProducts() : ProductResponse

     @GET("/product/get/men")
     suspend fun getMenProducts():ProductResponse

     @GET("/product/{id}")
     suspend fun getSpecificProduct(@Path("id") id: ObjectId): ResponseSpecificProduct
     //Add to cart ⬇️⬇️⬇️
     @POST("/user/addtocart")
     suspend fun addToCart(@Body request: AddToCartRequest): AddToCartResponse

     @POST("/user/removefromcart")
     suspend fun removeFromCart(@Body request: RemoveFromCart): RemoveFromCartResponse

     //get Cart
     @GET("/user/{id}/getcart")
     suspend fun getCart(@Path("id") id: ObjectId) : GetCartResponse


 /**
  * The user id above should be fetched from user credentials
  */


}

data class ResponseSpecificProduct(

  var message : String,
 var product: Product
)

data class AddToCartResponse(
 var message: String
)

data class GetCartResponse(
 var message: String,
 var usercart:List<String>,

 var products : List<ResponseProduct>

)


data class LoginRequest(
 val email:String,
 val password:String
)
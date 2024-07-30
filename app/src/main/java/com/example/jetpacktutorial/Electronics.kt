package com.example.jetpacktutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun Electronics(navController: NavController,apiService: ApiService){
    var vehicles by remember { mutableStateOf<List<Vehicle>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
             try {
                       vehicles = apiService.getVehicles().allUsers
                 } catch (e: Exception) {
                    // Handle the error appropriately
                     Log.d("Error while Loading ","Error hai bhai ${e.message}")
                 } finally {
                     isLoading = false
                 }
             }

       if (isLoading) {
               Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                         Text("Loading data ", color = Color.Yellow)
                     }
            } else {
                 LazyColumn {
                         items(vehicles) { vehicle ->
                            ProductItem(vehicle)
                        }
                    }
           }


}

@Composable
fun ProductItem(vehicle: Vehicle) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(75.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
        ) {
        Text(text = vehicle.name, color = Color.Yellow)
        Log.d("names",vehicle.name)

    }


}


/**
 * fun ProductScreen() {
 *     var products by remember { mutableStateOf<List<Product>>(emptyList()) }
 *     var isLoading by remember { mutableStateOf(true) }
 *
 *     LaunchedEffect(Unit) {
 *         try {
 *             products = apiService.getProducts()
 *         } catch (e: Exception) {
 *             // Handle the error appropriately
 *         } finally {
 *             isLoading = false
 *         }
 *     }
 *
 *     if (isLoading) {
 *         Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
 *             CircularProgressIndicator()
 *         }
 *     } else {
 *         LazyColumn {
 *             items(products) { product ->
 *                 ProductItem(product)
 *             }
 *         }
 *     }
 * }
 *
 * // Composable function to display individual product items
 * @Composable
 * fun ProductItem(product: Product) {
 *     Row(
 *         modifier = Modifier
 *             .fillMaxWidth()
 *             .padding(8.dp)
 *     ) {
 *         // Display product name and price
 *         Column(modifier = Modifier.weight(1f)) {
 *             Text(
 *                 text = product.name,
 *                 style = MaterialTheme.typography.h6
 *             )
 *             Text(
 *                 text = "\$${product.price}",
 *                 style = MaterialTheme.typography.body1
 *             )
 *         }
 *         // Placeholder for product image, replace with actual image loading logic
 *         Text(
 *             text = "Image",
 *             modifier = Modifier.align(Alignment.CenterVertically)
 *         )
 *     }
 * }
 *
 *
 *
 *
 */
///Ye lag raha hai ho jayega
/**
 *
 * import android.os.Bundle
 * import androidx.activity.ComponentActivity
 * import androidx.activity.compose.setContent
 * import androidx.compose.foundation.layout.*
 * import androidx.compose.foundation.lazy.LazyColumn
 * import androidx.compose.foundation.lazy.items
 * import androidx.compose.material.*
 * import androidx.compose.runtime.*
 * import androidx.compose.ui.Alignment
 * import androidx.compose.ui.Modifier
 * import androidx.compose.ui.unit.dp
 * import retrofit2.*
 * import retrofit2.converter.gson.GsonConverterFactory
 * import retrofit2.http.GET
 * import kotlinx.coroutines.*
 *
 * // Define the Product data class
 * data class Product(
 *     val id: String,
 *     val name: String,
 *     val price: Double,
 *     val imageUrl: String
 * )
 *
 * // Define the ApiService interface
 * interface ApiService {
 *     @GET("products")
 *     suspend fun getProducts(): List<Product>
 * }
 *
 * // Create Retrofit instance
 * val retrofit = Retrofit.Builder()
 *     .baseUrl("https://api.yourserver.com/")
 *     .addConverterFactory(GsonConverterFactory.create())
 *     .build()
 *
 * val apiService: ApiService = retrofit.create(ApiService::class.java)
 *
 * // Main activity
 * class ProductPage : ComponentActivity() {
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContent {
 *             ProductScreen()
 *         }
 *     }
 * }
 *
 * // Composable function to display the product screen
 * @Composable
 * fun ProductScreen() {
 *     var products by remember { mutableStateOf<List<Product>>(emptyList()) }
 *     var isLoading by remember { mutableStateOf(true) }
 *
 *     LaunchedEffect(Unit) {
 *         try {
 *             products = apiService.getProducts()
 *         } catch (e: Exception) {
 *             // Handle the error appropriately
 *         } finally {
 *             isLoading = false
 *         }
 *     }
 *
 *     if (isLoading) {
 *         Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
 *             CircularProgressIndicator()
 *         }
 *     } else {
 *         LazyColumn {
 *             items(products) { product ->
 *                 ProductItem(product)
 *             }
 *         }
 *     }
 * }
 *
 * // Composable function to display individual product items
 * @Composable
 * fun ProductItem(product: Product) {
 *     Row(
 *         modifier = Modifier
 *             .fillMaxWidth()
 *             .padding(8.dp)
 *     ) {
 *         // Display product name and price
 *         Column(modifier = Modifier.weight(1f)) {
 *             Text(
 *                 text = product.name,
 *                 style = MaterialTheme.typography.h6
 *             )
 *             Text(
 *                 text = "\$${product.price}",
 *                 style = MaterialTheme.typography.body1
 *             )
 *         }
 *         // Placeholder for product image, replace with actual image loading logic
 *         Text(
 *             text = "Image",
 *             modifier = Modifier.align(Alignment.CenterVertically)
 *         )
 *     }
 * }
 *
 */
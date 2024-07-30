package com.example.jetpacktutorial

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun Womensfashion(navController: NavController,apiService: ApiService) {
    var productsWomen by remember { mutableStateOf<List<Product>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }


    LaunchedEffect(Unit) {
        try {
            productsWomen = apiService.getWomenProducts().products

        } catch (e: Exception) {
            Log.d("Error while fetching women's product", "Error : ${e.message}")


        } finally {
            isLoading = false
        }

    }
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading data .... ", color = Color.Yellow)
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar()
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(padding)
                    .background(Color(0xFFFFF5E1)),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "This is women's fashion page", color = Color(0xFFFF6969))
                LazyColumn {
                    items(productsWomen) { product ->
                        Row(modifier =  Modifier.fillMaxWidth()){
                            displaycard(product = product, navController)
                            displaycard(product = product, navController =navController )

                        }

                    }
                }


            }

        }
    }
}










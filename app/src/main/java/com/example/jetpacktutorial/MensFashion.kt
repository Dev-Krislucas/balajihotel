package com.example.jetpacktutorial

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.bson.types.ObjectId

import androidx.navigation.NavController

@Composable
fun Mensfashion(navController: NavController){

    var productsMen by remember {
        mutableStateOf<List<Product>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    //Loading data using coroutine 👇👇
    LaunchedEffect(Unit){
        try{
            productsMen = apiService.getMenProducts().products

        }catch (e : Exception){
            Log.d("Error fetching mens data","Error : ${e.message}")
        }finally {
            isLoading = false
        }

    }

    if(isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading data .... ", color = Color.Yellow)
//        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        }
    }
    else{
        Scaffold (
            topBar = {
                TopAppBar()
            },
            bottomBar = {
                BottomNavigationBar(navController = navController)
            }
        ){
                paddingValues ->
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(paddingValues)
                .background(Color(0xFFFFF5E1)), verticalArrangement = Arrangement.Top,horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "This is men's fashion page", color = Color(0xFFFF6969))
                LazyColumn {
                    items(productsMen){product->
                        Row(modifier = Modifier.fillMaxWidth()){
                            displaycard(product = product, navController = navController)
                            displaycard(product = product, navController = navController)



                        }

                    }
                }


            }
        }
    }

    
}



@Composable
fun displaycard(product: Product,navController: NavController){
    Log.d("Product details ","Id : ${product._id}")


    val objectid = ObjectId(product._id)


    Log.d("fetching data from","BASE/product/${objectid}")



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("product/${product._id}")
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
    ){
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("${product.name}", modifier = Modifier.clickable{


                navController.navigate("product/${product._id}")
            }.border(2.dp,Color.Blue))
        }

    }



}
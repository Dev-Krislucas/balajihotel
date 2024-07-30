package com.example.jetpacktutorial

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.bson.types.ObjectId
import retrofit2.HttpException

@Composable
fun Cart(navController: NavController){
    var context = LocalContext.current
    var products by remember {
        mutableStateOf<List<ResponseProduct>>(emptyList())
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(Unit) {
        try{
            Log.d("From cart Page" ,"+++++++++++++++")

             products  = apiService.getCart((ObjectId(getUserId(context)))).products
            Log.d("From cart page loading products ", "${products}")
        }catch (e :HttpException){

            Log.d("HTTP Exceptin","Error : ${e.message}")
            val errorResponse = e.response()
            val errorBody = errorResponse?.errorBody()?.string()
            val statusCode = e.code()
            val headers = errorResponse?.headers()

            Log.e("HTTP Exception", "Status Code: $statusCode")
            Log.e("HTTP Exception", "Headers: $headers")
            Log.e("HTTP Exception", "Error Body: $errorBody")


        }catch (e : Exception){
            Log.d("General Exception","${e.message}")

        }finally {
            isLoading=false
        }


    }
    if(isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Loading data .... ", color = Color.Yellow)
        }
    }
        else{
            Scaffold(
                topBar = {
                    TopAppBar()
                },
                bottomBar = {
                    BottomNavigationBar(navController = navController)

                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues = paddingValues)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color(0xFFFFF5E1)),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    Text(text = "This is cart  page", color = Color(0xFFFF6969))
                    cartTotal(list = products)

                    LazyColumn {
                        items(products){product->
                            cartCard(product = product)
                    }

                    }



                }


            }

    }

    

}

@Composable
fun cartTotal(list : List<ResponseProduct>){
    var cartTotal = 0
    for(product in list){
        cartTotal+=product.price
            
    }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp), horizontalAlignment = Alignment.End
        ) {
            Text("Total : ₹ $cartTotal", style = TextStyle(
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
                fontSize = 15.sp
            )
            )
        }

    }


    


@Composable
fun cartCard(product: ResponseProduct){
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)


            ,

        elevation = 2.dp,
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFFFF5E1)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            ) {
            Image(painter = painterResource(id = R.drawable.bannerman), modifier = Modifier
                .height(130.dp)
                .width(130.dp)
                .clip(
                    RoundedCornerShape(10.dp)

                )
                ,contentDescription = "Cart Image")
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(15.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.SpaceBetween) {
                Text("${product.name.capitalize()}", style = TextStyle(
                    color = Color(0xFF0C1844),
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    letterSpacing = 4.sp
                ))
                    Spacer(modifier = Modifier.height(50.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "₹ ${product.price}", style = TextStyle(
                        color = Color(0xFF0C1844),

                        ))
                    Icon(imageVector = Icons.Default.Delete , tint = Color(0xFFC80036),contentDescription ="Delete from cart", modifier = Modifier.clickable {
                        coroutineScope.launch {
                            try{
                                val response = apiService.removeFromCart(RemoveFromCart(getUserId(context),product._id))
                                Log.d("Deleted !!!!","${response.message}")
                                Toast.makeText(context,"${response.message}",Toast.LENGTH_SHORT).show()
                            }catch (e:HttpException){
                                Log.d("HTTP Exceptin","Error : ${e.message}")
                                val errorResponse = e.response()
                                val errorBody = errorResponse?.errorBody()?.string()
                                val statusCode = e.code()
                                val headers = errorResponse?.headers()

                                Log.e("HTTP Exception", "Status Code: $statusCode")
                                Log.e("HTTP Exception", "Headers: $headers")
                                Log.e("HTTP Exception", "Error Body: $errorBody")

                            }
                            catch (e:Exception){
                                Log.d("General Exception","${e.message}")

                            }
                        }
                    })
                }

            }

        }













    }


}
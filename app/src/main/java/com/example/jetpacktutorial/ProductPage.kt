package com.example.jetpacktutorial

import android.media.Image
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.bson.types.ObjectId
import retrofit2.HttpException




@Composable
fun imageCarousel(image : Int){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .background(Color(0xFFFFF5E1))
//        .border(2.dp,Color.Black)

    ){

        Image(painter = painterResource(id = image), contentDescription = "Product image",
            modifier = Modifier
                .height(320.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
            , contentScale = ContentScale.FillBounds







        )

    }

}
@Composable
fun nameAndPrice(product: Product){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 35.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    
    ) {
        Text(text = product.name.capitalize(), style = TextStyle(
             color = Color.Black,
            fontSize = 23.sp,
            letterSpacing = 4.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.ExtraBold




        )
        , color = Color.Black)
        Text(text = "₹ ${product.price}", color = Color.Gray)



    }


}

@Composable
fun description(product: Product){


}

@Composable
fun addTocart(product: Product){
    val coroutineScope = rememberCoroutineScope()
    var context = LocalContext.current
    Log.d("user id" , "ID +++++ ${getUserId(context)}")

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp) ,
        horizontalArrangement = Arrangement.SpaceAround) {
        Button(

            onClick = {
                coroutineScope.launch {
                    try{
                        Log.d("User id" ,"${getUserId(context)}")
                        Log.d("Product id ", "${product._id}")
                        var response = apiService.addToCart(AddToCartRequest(getUserId(context),product._id))
                        Toast.makeText(context,"Item added To your cart",Toast.LENGTH_SHORT).show()
                        Log.d("Response","Response on success : ${response.message}")

                    }catch (e :HttpException){
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
                        Log.d("General exception","Error : ${e.message}")


                    }

                }



            }
            ,   colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC80036)
            )

        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(1.dp)
            ){


                Icon(imageVector = Icons.Default.ShoppingCart,tint=Color.White, contentDescription = "Add To caret logo")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Add To Cart",
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 15.sp
                    )
                )

            }



        }
        Button(
            onClick = { /*TODO*/ }
            ,   colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC80036)
            )




        ) {


            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(2.dp)

            ) {
                Icon(imageVector = Icons.Default.CheckCircle,tint=Color.White, contentDescription = "Add To caret logo")
//                Spacer(modifier = Modifier.width(10.dp))
//
//
//                Text("Buy Now",color=Color.White, style = TextStyle(
//                    fontFamily = FontFamily.Monospace
//                ))

            }



        }

    }

}


@Composable
fun ProductPage(navController: NavController,id:ObjectId) {


    var context = LocalContext.current

    var product by remember {
        mutableStateOf(
            Product(
                _id = "",
                name = "",
                image = emptyList<String>(),
                price = "",
                category = "",
                description = "",

                )
        )
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    Log.d("Inside product page", "BASE/${getUserId(context)}/cart")
    LaunchedEffect(Unit) {
        try {
            product = apiService.getSpecificProduct(id).product

        } catch (e: Exception) {
            Log.d("Error from display product page", "Message : ${e.message}")


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
            },

            ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(0xFFFFF5E1))
            ) {
                imageCarousel(image = R.drawable.bannerman)
                nameAndPrice(product = product)
                addTocart(product = product)


            }

        }
    }
}









/**
 *  LaunchedEffect(Unit) {
 *         try {
 *             product = apiService.getSpecificProduct(id).product
 *
 *         } catch (e: Exception) {
 *             Log.d("Error from display product page", "Message : ${e.message}")
 *
 *         } finally {
 *             isLoading = false
 *
 *         }
 *     }
 */



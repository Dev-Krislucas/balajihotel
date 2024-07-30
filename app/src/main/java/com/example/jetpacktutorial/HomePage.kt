package com.example.jetpacktutorial

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun ImageCarousel(imageList:List<Painter>){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp


    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 30.dp)
        .border(2.dp, Color.Black), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center

    )

    {
        items(imageList){ imageUrl->
            Image(painter = imageUrl, contentDescription = "Carousel Images", modifier = Modifier.width(screenWidth))
            


        }
    }
}

//banner images

@Composable
fun BannerImages(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp, horizontal = 10.dp),

        horizontalArrangement = Arrangement.SpaceAround
    ){
        Image(painter = painterResource(id = R.drawable.bannerman), contentDescription ="Try image",
                modifier = Modifier
                    .height(170.dp)
                    .width(170.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        navController.navigate("mens-fashion")
                    }

            ,
            contentScale = ContentScale.FillBounds, // Set how the image should scale to fill its bounds


        )
        Image(painter = painterResource(id = R.drawable.bannerwoman), contentDescription ="Try image",
            modifier = Modifier
                .height(170.dp)
                .width(170.dp)
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    navController.navigate("womens-fashion")
                }

                ,
            contentScale = ContentScale.FillBounds,
            // Set how the image should scale to fill its bounds

        )


    }
}





@Composable
fun CustomTextField() {
    var query by remember { mutableStateOf("") }
Column(
    horizontalAlignment = Alignment.CenterHorizontally,

    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 30.dp)
//        .border(2.dp,Color.Black)
    
){
    OutlinedTextField(value = query,
        modifier = Modifier.fillMaxWidth(),
        onValueChange ={ newVal->query = newVal},
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFC80036),
            unfocusedBorderColor = Color.Black,
            focusedLabelColor = Color(0xFFC80036),
            focusedTextColor = Color(0xFFC80036),
            unfocusedLabelColor = Color.Black


        ),
        shape = RoundedCornerShape(30.dp),
        label = {Text("Search", fontFamily = FontFamily.Serif)}




    )
}

}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
    CustomTextField()
}



@Composable
fun CouponCode(){
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .background(Color.Transparent)
//        .border(1.dp,Color.Black, shape = RoundedCornerShape(20.dp))

        .padding(all = 20.dp)
        )

    {


            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(20.dp))
//                .border(1.dp, Color.Black, shape = RoundedCornerShape(20.dp))
                .background(
                    Color(0xFFFFE26C)


                )


                , verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally


            ){
                    Text(text = "Coupon for Firts time Users ..", color = Color.Black, fontFamily = FontFamily.Cursive)
                    Text(text = "KFLY123",color = Color.Black, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
            }

    }
}



//
@Composable
fun Greet(userName:String?){
//    Log.d("Inside Greeet functiom","received name : ${userName}")
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

         Text("Welcome ${userName?:"User"}", style = TextStyle(
             color = Color.Gray,
             fontFamily = FontFamily.Cursive,
             fontSize = 25.sp, letterSpacing = 5.sp

         )
         )


    }
}
//





@Composable
fun HomePage(navController: NavController,userName:String?){
    val context = LocalContext.current

    Log.d("From home page","User id : ${getUserId(context)}")
    Log.d("From home page","User Name : ${getUserName(context)}")


    Scaffold(
       topBar = {
           TopAppBar()
       },
       bottomBar = {
           BottomNavigationBar(navController)

       }

   ) {padding->
       Column(modifier = Modifier
           .padding(padding)
           .fillMaxWidth()
           .fillMaxHeight()
           .background(Color(0xFFFFF5E1))){

           Greet(userName)
           //CustomTextField()
           BannerImages(navController)
           CouponCode()



       }


   }
}



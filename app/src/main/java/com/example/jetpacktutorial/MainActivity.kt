package com.example.jetpacktutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.example.jetpacktutorial.ui.theme.Electronics
import com.example.jetpacktutorial.ui.theme.JetpackTutorialTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.bson.types.ObjectId


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            JetpackTutorialTheme {

                MyApp()
            }
        }
    }
}
data class Books(val name : String,val bookName : String)


@Composable
fun Birthdaycard(name:String , from: String){
    Column(modifier = Modifier.padding(all= 20.dp), verticalArrangement = Arrangement.Center){
        Text("Happy Birthday ${name}", color = Color.White,textAlign = TextAlign.Center, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(all = 20.dp)


        )
        Text("Regards from ${from}" , color = Color.White,fontSize = 40.sp, lineHeight = 50.sp,textAlign = TextAlign.Center)


    }


}

@Composable
fun GreetAuthor( Book : Books) {
    Column(modifier = Modifier
        .background(Color(0xFFEE1415))
        .padding(all = 10.dp)
        .fillMaxWidth()
        .fillMaxWidth()) {
//        Image(painter = painterResource(id = R.drawable.profile), contentDescription ="YE mera profile h" )
            Row(){
                Text("Devarshi ")
                Text("${Book.name}")
            }

        Text(" You have written ${Book.bookName}")

    }

}
@Composable
fun Draw(name :String){
   Surface(
       shadowElevation = 5.dp,
       shape= RoundedCornerShape(12.dp),
       color = MaterialTheme.colorScheme.background,

       modifier = Modifier

           .fillMaxWidth()
           .fillMaxHeight()
           .padding(all = 15.dp)
   ) {
        Text(text = "From Surface : ${name}", color = Color(0xFFEE1415) , modifier = Modifier.padding(10.dp))

   }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(){
         val navController = rememberNavController()




        NavHost(navController = navController, startDestination = "login") {
            composable(route = BottomNavigation.Cart.route){ Cart(navController = navController)}
            composable(route = "login") { LoginScreen(navController) }
            composable(route = "signup") { SignupScreen(navController) }
            composable(route="home/{username}"){backStack ->
                var user = backStack.arguments?.getString("username")
                Log.d("From Main Activity : ", "Received : $user")

                HomePage(navController = navController, userName = user)

            }
            composable("${BottomNavigation.Home.route}/{username}"){backStack->
                var userName = backStack.arguments?.getString("username")
                HomePage(navController, userName = userName)

            }
            composable("electronics"){ Electronics(navController, apiService = apiService)}
            composable("mens-fashion"){ Mensfashion(navController = navController)}
            composable("womens-fashion"){ Womensfashion(navController = navController, apiService)}


            composable("product/{id}"){backStack->
                var id = backStack.arguments?.getString("id")
                var toObject = ObjectId(id)
                ProductPage(navController = navController,id=toObject)


            }
        }








    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = Color(0xFFFF6969), // Change this to any color you want
        darkIcons = false
    )
    systemUiController.isNavigationBarVisible = false
//    systemUiController.setNavigationBarColor(
//        color = Color(0xFFFF6969),
//        darkIcons = false
//    )

}


@Preview(name  = "My preview")
@Composable fun Preview(){
    GreetAuthor(Books("Mark Manosn","Nobody gives a fuck"))
    Draw("Rishi Kapoor")
}




//Preview function can't take any parameters


//    Column(modifier = Modifier.verticalScroll(rememberScrollState())){
//        Surface(
//            shape = RoundedCornerShape(10.dp)
//            ,modifier = Modifier
//                .padding(all = 30.dp)
//                .height(300.dp)
//                .fillMaxHeight()
//                .shadow(10.dp), color = Color(0xFFEE1415)
//        ) {
//            Birthdaycard(name = "Thankamma das", from ="Antony Das" )
//        }
//        Surface(
//            shape = RoundedCornerShape(10.dp)
//            ,modifier = Modifier
//                .padding(all = 30.dp)
//                .height(300.dp)
//                .fillMaxHeight()
//                .shadow(10.dp), color = Color(0xFFFFB200)
//        ) {
//            Birthdaycard(name = "Karan Aujla", from ="Vicky kaushal" )
//        }
//        Surface(
//            shape = RoundedCornerShape(10.dp)
//            ,modifier = Modifier
//                .padding(all = 30.dp)
//                .height(300.dp)
//                .fillMaxHeight()
//                .shadow(10.dp), color = Color(0xFFEB5B00)
//        ) {
//            Birthdaycard(name = "Ammy Virk", from ="Tripti Dimri" )
//        }
//
//
//    }
//
//
//
//}



//C80036 Fr font
//FFF5E1 Body
//C80036
//0C1844 Font actual
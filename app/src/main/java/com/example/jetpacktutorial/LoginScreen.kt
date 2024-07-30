package com.example.jetpacktutorial

import android.net.http.HttpException
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
//import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun LoginScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var context = LocalContext.current


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
//            .padding(all = 16.dp)
            .background(Color(0xFFFFF5E1)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {
        Image(painter = painterResource(id = R.drawable.logo2), contentDescription ="Logo image",modifier = Modifier
            .height(120.dp)
            .width(240.dp), contentScale = ContentScale.Crop)

        OutlinedTextField(value = email, onValueChange ={ email = it} , colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color(0xFFFF6969),
            unfocusedTextColor = Color(0xFFFF6969),

            focusedBorderColor = Color(0xFFFF6969),
            unfocusedBorderColor = Color(0xFFFF6969)
        ),
            label={Text("Email", style= TextStyle(
                fontFamily = FontFamily.Monospace
                ,color = Color(0xFFFF6969)
            ))},)

        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(value = password, onValueChange = {password = it},
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedTextColor = Color(0xFFFF6969),

                focusedTextColor = Color(0xFFFF6969),
                focusedBorderColor = Color(0xFFFF6969),
                unfocusedBorderColor = Color(0xFFFF6969)

            ),
            label={Text("Password", style = TextStyle(
                fontFamily = FontFamily.Monospace
                ,color = Color(0xFFFF6969)

            ))},
            textStyle = TextStyle(color = Color(0xFFFF6969)))
        Spacer(modifier = Modifier.height(25.dp))


        Button(
            onClick = {
                coroutineScope.launch  {
                    try{
                        var response = apiService.login(LoginRequest(email,password))
                        var user = response.user
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context,"Logged In : ${response.token}", Toast.LENGTH_LONG).show()
                            saveUserDetails(context,user._id,user.name)

                            navController.navigate("home/${user.name}")

                        }
                    }catch (e:Exception){
                        Log.d("General Exception","${e.message}")

                    }catch(e: retrofit2.HttpException){
                        Log.d("HTTP Exception","${e.message}")

                    }

                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC80036)

            )



        ) {
            Text("Login", style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                letterSpacing = 3.sp
                , color = Color.White
            ))

        }
        Spacer(modifier = Modifier.height(35.dp))

        Text("Not Signed Up ? Sign Up", style = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 18.sp,
            fontWeight = FontWeight.ExtraBold
            ,color = Color(0xFFFF6969)
        ), modifier = Modifier.clickable{
            navController.navigate("signup")
        })


    }
}

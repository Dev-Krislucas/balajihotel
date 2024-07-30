package com.example.jetpacktutorial

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


@Composable
fun SignupScreen(navController: NavController){


    var name by remember { mutableStateOf("") }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    val context = LocalContext.current



    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(Color(0xFFFFF5E1)), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.logo2), contentDescription ="Logo image",modifier = Modifier
            .height(120.dp)
            .width(240.dp), contentScale = ContentScale.Crop)


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(value = name,
//                modifier = Modifier.fillMaxWidth(),
                onValueChange ={ newVal->name = newVal},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFF6969),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFFFF6969),
                    focusedTextColor = Color(0xFFFF6969),


                ),
                shape = RoundedCornerShape(10.dp),
                label = {Text("Name",fontFamily = FontFamily.Monospace)}




            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = email,
//                modifier = Modifier.fillMaxWidth(),
                onValueChange ={ newVal->email = newVal},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFF6969),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFFFF6969),
                    focusedTextColor = Color(0xFFFF6969),


                    ),
                shape = RoundedCornerShape(10.dp),
                label = {Text("Email",fontFamily = FontFamily.Monospace)}




            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = password,
//                modifier = Modifier.fillMaxWidth(),
                onValueChange ={ newVal->password = newVal},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFF6969),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFFFF6969),
                    focusedTextColor = Color(0xFFFF6969),


                    ),
                shape = RoundedCornerShape(10.dp),
                label = {Text("Password",fontFamily = FontFamily.Monospace)},





            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = city,
//                modifier = Modifier.fillMaxWidth(),
                onValueChange ={ newVal->city = newVal},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFF6969),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFFFF6969),
                    focusedTextColor = Color(0xFFFF6969),


                    ),
                shape = RoundedCornerShape(10.dp),
                label = {Text("City",fontFamily = FontFamily.Monospace)}




            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = number,
//                modifier = Modifier.fillMaxWidth(),
                onValueChange ={ newVal->number = newVal},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFFF6969),
                    unfocusedLabelColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color(0xFFFF6969),
                    focusedTextColor = Color(0xFFFF6969),


                    ),
                shape = RoundedCornerShape(10.dp),
                label = {Text("Number", fontFamily = FontFamily.Monospace)}




            )
            Spacer(modifier = Modifier.height(10.dp))
        }


        Button(
            onClick = {

                CoroutineScope(Dispatchers.IO).launch{

                    try {

                        val user = User(name, email, password,city,number)


                        val response = apiService.signup(user)

                        Log.d("Response token" , "Token : ${response.token}")
                        Log.d("New user","New user +++ ${response.newUser}")


                        withContext(Dispatchers.Main) {
                            Toast.makeText(context,"Signed Up : ${response.token}",Toast.LENGTH_LONG).show()
                            saveUserDetails(context,response.newUser._id,response.newUser.name)

                            navController.navigate("home/${user.name}")

                        }




                    } catch (e: IOException) {
                        Log.d("IOEXCEPTION","Error : ${e.message}")


                    } catch (e: HttpException) {
                        Log.d("HttpException","Error : ${e.message}")


                    } catch (e: Exception) {
                        Log.d("General Exception","Error : ${e.message}")

                    } finally {
                        Log.d("Inside Finally Statement","Response : 👋")

                    }
                }
//                navController.navigate(("home")




            },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC80036)
            )


        ) {
            Text("Sign up", color = Color.White)
        }
    }
}


/**
 *
 *  Button(onClick = {
 *             isLoading = true
 *             LaunchedEffect(Unit) {
 *                 try {
 *                     val user = User(name, email, city, password)
 *                     val response = apiService.signup(user)
 *                     Toast.makeText(context, response.message, Toast.LENGTH_LONG).show()
 *                 } catch (e: IOException) {
 *                     Toast.makeText(context, "Network error: ${e.message}", Toast.LENGTH_LONG).show()
 *                 } catch (e: HttpException) {
 *                     Toast.makeText(context, "Server error: ${e.message}", Toast.LENGTH_LONG).show()
 *                 } catch (e: Exception) {
 *                     Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
 *                 } finally {
 *                     isLoading = false
 *                 }
 *             }
 */
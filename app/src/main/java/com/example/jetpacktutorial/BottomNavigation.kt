package com.example.jetpacktutorial

//import android.graphics.drawable.Icon
import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart

sealed class BottomNavigation(val route : String,val icon : ImageVector,val title : String) {
    object Home :BottomNavigation("home/{username}", Icons.Default.Home   , title = "Home")

    object Cart : BottomNavigation("cart",Icons.Default.ShoppingCart,"Cart")

    //Future references here 👇👇👇








}


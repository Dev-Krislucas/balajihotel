package com.example.jetpacktutorial

import android.content.Context
import android.util.Log
import org.json.JSONObject
import org.json.JSONTokener

fun saveUserDetails(context: Context, userId: String, userName: String) {
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    editor.putString("user_id", userId)
    editor.putString("user_name", userName)

    editor.apply()
}
fun getUserId(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("user_id", null)
}

fun getUserName(context: Context): String? {
    val sharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    return sharedPreferences.getString("user_name", null)
}

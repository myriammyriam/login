package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.data.DataProvider

class First_Page : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            Homepage()
        }
    }
}

@Composable
fun Homepage() {


    val list = remember { DataProvider.Listanimes }
    val context = LocalContext.current
    val PREFS_NAME = "myPrefs"
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    Column {
        Button(onClick = {
           prefs.edit().remove("username").apply()
            prefs.edit().remove("password").apply()

            val savedUsername = prefs.getString("username","")
            val savedPassword = prefs.getString("password","")



            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
           // println("FirstPage : $savedUsername $savedPassword")

        }) {
            Text(text = "Se déconnecter")
        }
        LazyColumn(contentPadding = PaddingValues(horizontal = 20.dp, vertical = 50.dp)) {
            items(list) {
                AnimeList(anime = it, context)
            }
        }


    }
}
/*@Composable
fun Greeting() {
    val context = LocalContext.current

    val PREFS_NAME = "myPrefs"
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)








        Button(onClick = {
            prefs.edit().remove("username").apply()
            prefs.edit().remove("password").apply()


            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)

        }) {
            Text(text = "Se déconnecter")
        }
    }*/
@Preview(showBackground = true)
@Composable
fun App2(){
    Homepage()}


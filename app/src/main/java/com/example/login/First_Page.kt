package com.example.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class First_Page : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val getusername = intent.getStringExtra("username") ?: "no_data"
        val getpassword = intent.getStringExtra("password") ?: "no_data"

        setContent {
            Greeting(getusername, getpassword)
        }
    }
}

@Composable
fun Greeting(username: String, password: String) {
    val context = LocalContext.current

    val PREFS_NAME = "myPrefs"
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(100.dp)
    ) {
        Text(text = "Nom d'utilisateur: $username", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(32.dp))
        Text(text = "Mot de passe: $password", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        Button(onClick = {
            prefs.edit().remove("username").apply()
            prefs.edit().remove("password").apply()


            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)

        }) {
            Text(text = "Se déconnecter")
        }
    }
}

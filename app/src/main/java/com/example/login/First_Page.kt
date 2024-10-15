package com.example.login

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.ui.theme.LoginTheme

class First_Page : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val getusername = intent.getStringExtra("username") ?: "no_data"
        val getpassword = intent.getStringExtra("password") ?: "no_data"
        setContent {

            Greeting(getusername,getpassword)

        }
    }
}

@Composable
fun Greeting(username:String,password:String){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
        , modifier = Modifier.padding(100.dp)) {
        Text(text = "username: "+username, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.padding(32.dp))
        Text(text = "password: "+password, fontSize = 18.sp, fontWeight = FontWeight.Bold)
    }



}






/*@Composable
@Preview(showBackground = true)
fun App3(){
 Greeting(username = String, password= String)}*/


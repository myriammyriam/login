package com.example.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.ui.theme.LoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SignInScreen()

        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun SignInScreen(){

    val context= LocalContext.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordvisibility by remember { mutableStateOf(false) }
    val icon =if(passwordvisibility) painterResource(id=R.drawable.baseline_visibility_24)
    else
        painterResource(id = R.drawable.baseline_visibility_off_24)

    var Isformvalid by remember { mutableStateOf(false) }

    if(username.isNotBlank()&&password.isNotBlank()) Isformvalid=true
    else Isformvalid =false



    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Error")
            },
            text = {
                Text(text = "Check your information")
            },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                }) {
                    Text("OK")
                }})
    }


    Column (modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.signin), contentDescription = "Login image"
           , modifier = Modifier.size(250.dp))

        Text(text = "Welcom Back", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Login to your account")
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = username, onValueChange = {username=it}, label = {
            Text(text = "User Name")
        },leadingIcon = {
            Icon(imageVector = Icons.Filled.Edit,
                contentDescription ="icon" )}
        ,shape = RoundedCornerShape(20.dp))
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = password, onValueChange = {password=it}, label = {
            Text(text = "Password")
        }
        , leadingIcon = { Icon(imageVector =Icons.Filled.Lock, contentDescription = "icon") }
        ,shape = RoundedCornerShape(20.dp)
        , trailingIcon = {
            IconButton(onClick = {
                passwordvisibility=!passwordvisibility
            }) {
                Icon(painter = icon, contentDescription = "icon")
            }
            }, visualTransformation = if (passwordvisibility) VisualTransformation.None
            else PasswordVisualTransformation()

            )


        Text(text = "Forgot Password?",
             textDecoration = TextDecoration.Underline
            , color = Color.Blue
            , modifier = Modifier
                .padding(start = 170.dp)
                .clickable {
                    val intent = Intent(context, ForgotPassword::class.java)
                    context.startActivity(intent)
                })
        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = {
            val intent=Intent(context,First_Page::class.java)
            if(!Isformvalid)
                showDialog=true
            else{
                intent.putExtra("username",username)
                intent.putExtra("password",password)
                context.startActivity(intent)}
        }, modifier = Modifier) {
            Text(text ="Sign In", fontSize = 30.sp )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
        Text(text = "Don't have an account? ")
        Text(text = " Sign Up", textDecoration = TextDecoration.Underline, color = Color.Blue, modifier = Modifier.clickable {
            val intent =Intent(context,SignUP::class.java)
            context.startActivity(intent)
        })
        }











    }
}



@Preview(showBackground = true)
@Composable
fun APP(){
    SignInScreen()
}


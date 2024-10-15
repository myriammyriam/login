package com.example.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.ui.theme.LoginTheme


class SignUP : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SignUPScreen()


        }
    }
}
@Composable
fun SignUPScreen(){


    val context = LocalContext.current

    var fullname by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpsw by remember { mutableStateOf("") }
    var passwordvisibility by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val icon =if(passwordvisibility) painterResource(id=R.drawable.baseline_visibility_24)
    else
        painterResource(id = R.drawable.baseline_visibility_off_24)

    var confirm by remember { mutableStateOf(false) }
    var Isformvalid by remember { mutableStateOf(false) }
    if(fullname.isNotBlank()&&username.isNotBlank()&&email.isNotBlank()&&password.isNotBlank()&&confirmpsw.isNotBlank()) Isformvalid=true
    else Isformvalid =false

    if(password==confirmpsw) confirm=true
    else confirm=false

    Column (modifier = Modifier.fillMaxSize().padding(bottom = 20.dp).verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(painter = painterResource(id = R.drawable.signup), contentDescription = "Login image"
        , modifier = Modifier.size(250.dp))

        Text(text = "Welcom ", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))

        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = fullname, onValueChange = {fullname=it}, label = {
            Text(text = "Full Name")
        }
            ,leadingIcon = {
                Icon(imageVector = Icons.Filled.Edit,
                    contentDescription ="icon" )}
        ,shape = RoundedCornerShape(20.dp))

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = username, onValueChange = {username=it}, label = {
            Text(text = "User Name")
        }
            ,leadingIcon = {
            Icon(imageVector = Icons.Filled.Person,
                contentDescription ="icon" )}
            ,shape = RoundedCornerShape(20.dp)

        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = email, onValueChange = {email=it}, label = {
            Text(text = "Email")
        }, placeholder = {(Text(text = "abc123@example.com"))}
            ,leadingIcon = {
                Icon(imageVector = Icons.Filled.Email,
                    contentDescription ="icon" )}
        ,shape = RoundedCornerShape(20.dp))
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = password, onValueChange = {password=it}, label = {
            Text(text = "Password")
        }
            ,leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock,
                    contentDescription ="icon" )}
        ,shape = RoundedCornerShape(20.dp)
            , trailingIcon = { IconButton(onClick = {
                passwordvisibility=!passwordvisibility
            }){
                Icon(icon, contentDescription = "icon")

            } }
            , visualTransformation = if (passwordvisibility) VisualTransformation.None
            else PasswordVisualTransformation() )
        Spacer(modifier = Modifier.height(16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = confirmpsw, onValueChange = {confirmpsw=it}, label = {
            Text(text = "Confirm Password")
        }
            ,leadingIcon = {
                Icon(imageVector = Icons.Filled.Check,
                    contentDescription ="icon" )}
        ,shape = RoundedCornerShape(20.dp), trailingIcon = {
            IconButton(onClick = {
                passwordvisibility=!passwordvisibility
            }) {
                Icon(painter = icon, contentDescription = "icon")
            }
            }
        , visualTransformation = if (passwordvisibility) VisualTransformation.None
        else PasswordVisualTransformation() )
        Spacer(modifier = Modifier.height(16.dp))






        Button(onClick = {
            val intent=Intent(context,First_Page::class.java)
            if(Isformvalid && confirm)
                context.startActivity(intent)
            else println("Check your information")

        }, modifier = Modifier) {
            Text(text ="Sign Up", fontSize = 30.sp )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Text(text = "Already have an account? ")
            Text(text = " Sign In", textDecoration = TextDecoration.Underline, color = Color.Blue
                , modifier = Modifier.clickable {
                   val intent =Intent(context,MainActivity::class.java)
                    context.startActivity(intent)
                } )
        }

        Text(text = " Or Sign Up With", modifier = Modifier.padding(top = 20.dp))
        Spacer(modifier = Modifier.height(30.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(start = 30.dp)) {
            Image(painter = painterResource(R.drawable.f), contentDescription = "facebook logo"
                , modifier = Modifier.clickable {  }.size(35.dp))
            Spacer(modifier = Modifier.padding(16.dp))

            Image(painter = painterResource(R.drawable.g), contentDescription = "google logo"
                , modifier = Modifier.clickable {  }.size(35.dp))
            Spacer(modifier = Modifier.padding(16.dp))

            Image(painter = painterResource(R.drawable.t), contentDescription = "twitter logo"
                , modifier = Modifier.clickable {  }.size(35.dp))
            Spacer(modifier = Modifier.padding(16.dp))

        }











    }
}



@Preview(showBackground = true)
@Composable
fun APP1(){
    SignUPScreen()
}


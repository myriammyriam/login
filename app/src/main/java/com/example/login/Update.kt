package com.example.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.data.Database

class Update : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val oldTitle = intent.getStringExtra("title")?:""
        val oldDate = intent.getStringExtra("date")?:""
        val oldDescription = intent.getStringExtra("description")?:""


        setContent {
            EditAnimeScreen(oldTitle, oldDate, oldDescription)
        }
    }
}


@Composable
fun EditAnimeScreen(oldTitle: String, oldDate: String, oldDescription: String) {
    val context = LocalContext.current
    val database = Database(context)


   // val OldTitle = intent.getStringExtra("title") ?: ""

    var title by remember { mutableStateOf(oldTitle) }
    var date by remember { mutableStateOf(oldDate) }
    var description by remember { mutableStateOf(oldDescription) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text(text = "Title") },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))


        TextField(
            value = date,
            onValueChange = { date = it },
            label = { Text(text = "Date") },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Description field
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text(text = "Description") },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                if (title.isNotBlank() && date.isNotBlank() && description.isNotBlank()) {
                   database.updateAnime(oldTitle, title, date, description, newBase64Image = "")
                    val intent = Intent(context, First_Page::class.java)
                    context.startActivity(intent)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Save Changes", fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                val intent = Intent(context, First_Page::class.java)
                context.startActivity(intent) // Navigate back without saving changes
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) {
            Text(text = "Cancel", fontSize = 20.sp)
        }
    }
}

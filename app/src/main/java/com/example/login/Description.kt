package com.example.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.login.ui.theme.LoginTheme
import com.example.login.data.Anime

class Description : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val description = intent.getStringExtra("description") ?: "no_data"
            val title = intent.getStringExtra("title") ?: "no_data"
            val date= intent.getStringExtra("date") ?: "no_data"
            val imageId= intent.getIntExtra("imageId", R.drawable.ic_launcher_background)


            decription(Anime(id = 0, title =title , date = date, description = description, imageId = imageId ))

        }
    }
}

@Composable
fun decription(anime: Anime){
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = anime.description, style = typography.bodySmall)
        Text("hello")
    }
}

package com.example.login

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import android.graphics.BitmapFactory
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.login.data.Anime
import com.example.login.utils.Utils
import java.io.ByteArrayInputStream

@Composable
fun AnimeList(anime: Anime, context: Context) {


    val imageUrl = Utils.Base64toBitmap(anime.imageBase64)


    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                val intent = Intent(context, Description::class.java)
                intent.putExtra("title", anime.title)
                intent.putExtra("description", anime.description)
                intent.putExtra("date", anime.date)
                intent.putExtra("imageId", anime.imageId)
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {

            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(84.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )


            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = anime.title, style = typography.titleLarge)
                Text(text = anime.date, style = typography.bodyMedium)
            }
        }
    }
}


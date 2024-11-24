package com.example.login

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.login.data.Database

import java.io.ByteArrayOutputStream

class AddNewAnime : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AddAnime()
        }
    }
}

@Composable
fun AddAnime() {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageBase64 by remember { mutableStateOf("") } // Stocke l'image en Base64
    var showDialog by remember { mutableStateOf(false) }


    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            imageBase64 = encodeImageToBase64(context, uri)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Error") },
            text = { Text(text = "Please fill in all fields and select an image") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text("OK")
                }
            }
        )
    }

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

                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (imageBase64.isNotEmpty()) "Image Selected" else "Select Image",
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.Default.Add, contentDescription = "Upload Image")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && date.isNotBlank() && description.isNotBlank() && imageBase64.isNotBlank()) {
                    val database = Database(context)
                    database.addAnime(title, date, description, imageBase64) //

                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                } else {
                    showDialog = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(text = "Add Anime", fontSize = 20.sp)
        }
    }
}

// image--> Base64
fun encodeImageToBase64(context: Context, uri: Uri): String {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(uri)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}

fun decodeBase64ToImage(base64String: String): Bitmap {
    val decodedString: ByteArray = Base64.decode(base64String, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}

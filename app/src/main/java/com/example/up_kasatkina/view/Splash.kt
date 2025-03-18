package com.example.up_kasatkina.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.up_kasatkina.R
import kotlinx.coroutines.delay

@Composable
fun Splash(navController: NavController){
    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate("Registr")
    }
    Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFF48B2E7))){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.splash),
                modifier = Modifier.size(200.dp),
                contentDescription = ""
            )
        }
    }
}
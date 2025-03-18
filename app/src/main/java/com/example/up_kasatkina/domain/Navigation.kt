package com.example.up_kasatkina.domain

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.up_kasatkina.view.Splash
import com.example.up_kasatkina.view.registr.Registr

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Splash") {

        composable("Splash") {
            Splash(navController)
        }
//        composable("Auth") {
//            Auth(navController)
//        }
        composable("Registr") {
            Registr(navController)
        }
//        composable("Film") {
//            Film(navController)
//        }
//        composable("Favourite") {
//            Favourite(navController)
//        }
    }
}
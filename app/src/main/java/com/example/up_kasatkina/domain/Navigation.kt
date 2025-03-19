package com.example.up_kasatkina.domain

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.up_kasatkina.view.Splash
import com.example.up_kasatkina.view.auth.Auth
import com.example.up_kasatkina.view.categoryProducts.CategoryProducts
import com.example.up_kasatkina.view.home.Home
import com.example.up_kasatkina.view.registr.Registr

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Splash") {

        composable("Splash") {
            Splash(navController)
        }
        composable("Auth") {
            Auth(navController)
        }
        composable("Registr") {
            Registr(navController)
        }
        composable("Home") {
            Home(navController)
        }
        // Добавляем категорию как параметр в URL
        composable("category_products/{category_id}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("category_id") ?: ""
            CategoryProducts(navController = navController, category = categoryId)
        }

    }
}
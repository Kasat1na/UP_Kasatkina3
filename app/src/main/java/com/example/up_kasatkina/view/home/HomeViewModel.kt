package com.example.up_kasatkina.view.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.up_kasatkina.domain.Constant
import com.example.up_kasatkina.model.actions
import com.example.up_kasatkina.model.categories
import com.example.up_kasatkina.model.favourite
import com.example.up_kasatkina.model.products
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    var products by mutableStateOf<List<products>>(listOf())
    var actions by mutableStateOf<List<actions>>(listOf())
    var categories by mutableStateOf<List<categories>>(listOf())
    var filteredProducts by mutableStateOf<List<products>>(listOf()) // Отфильтрованные товары
    var searchQuery by mutableStateOf("")
    var favourites by mutableStateOf<Set<String>>(setOf())
    fun showproducts() {
        viewModelScope.launch {
            try {
                products = Constant.supabase.from("products")
                    .select()
                    .decodeList<products>()
                filterProducts() // Фильтруем товары при загрузке
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }

    fun filterProducts() {
        filteredProducts = if (searchQuery.isBlank()) {
            products
        } else {
            products.filter { it.title.contains(searchQuery, ignoreCase = true) }
        }
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
        filterProducts()
    }

    fun showactions()
    {
        viewModelScope.launch {
            try{
                actions = Constant.supabase.from("actions")
                    .select()
                    .decodeList<actions>()
            }catch (e:Exception){
                Log.d("error", e.message.toString())
            }
        }
    }
    fun showcategories()
    {
        viewModelScope.launch {
            try{
                categories = Constant.supabase.from("categories").select().decodeList<categories>()
            }catch (e:Exception){
                Log.d("error", e.message.toString())
            }
        }
    }
    // Проверяет, какие товары в избранном у текущего пользователя
    fun checkIfFavourite() {
        viewModelScope.launch {
            try {
                val user = Constant.supabase.auth.currentUserOrNull()
                if (user != null) {
                    val favList = Constant.supabase.from("favourite")
                        .select()
                        .decodeList<favourite>()
                        .filter { it.user_id == user.id }
                        .map { it.product_id }
                        .toSet()  // Преобразуем в Set для удобства поиска

                    favourites = favList // Обновляем состояние
                }
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }

    // Добавление/удаление товара в избранное
    fun toggleFavourite(productId: String) {
        viewModelScope.launch {
            try {
                val user = Constant.supabase.auth.currentUserOrNull()
                if (user != null) {
                    if (favourites.contains(productId)) {
                        // Удаляем товар из избранного
                        Constant.supabase.from("favourite")
                            .delete { "user_id = '${user.id}' AND product_id = '$productId'" }

                        favourites = favourites - productId
                    } else {
                        // Добавляем товар в избранное
                        Constant.supabase.from("favourite")
                            .insert(favourite(user_id = user.id, product_id = productId))

                        favourites = favourites + productId
                    }
                }
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }



}
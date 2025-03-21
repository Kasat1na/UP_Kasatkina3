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
    var filteredProducts by mutableStateOf<List<products>>(listOf()) // отфильтрованные товары
    var searchQuery by mutableStateOf("") //строка поиска
    var favourites by mutableStateOf<Set<String>>(setOf()) //избранное
    fun showproducts() {
        viewModelScope.launch {
            try {
                products = Constant.supabase.from("products")
                    .select()
                    .decodeList<products>()
                filterProducts() // фильтруем товары при загрузке (поиск)
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }
//фильтрация товаров по поисковому запросу
    fun filterProducts() {
        //если пусто, то все иначе фильтруем по титлу
        filteredProducts = if (searchQuery.isBlank()) {
            products
        } else {
            products.filter { it.title.contains(searchQuery, ignoreCase = true) }
        }
    }
//обновляет searchQuery
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
    // проверяет какие товары в избранном у текущего пользователя
    fun checkIfFavourite() {
        viewModelScope.launch {
            try {
                val user = Constant.supabase.auth.currentUserOrNull()
                if (user != null) {
                    val favList = Constant.supabase.from("favourite")
                        .select()
                        .decodeList<favourite>()
                        .filter { it.user_id == user.id } //оставляем только текущ польз
                        .map { it.product_id } //получаем только товары
                        .toSet()  // удаляем дубликаты

                    favourites = favList // обновляем состояние
                }
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }

    // добавление/удаление товара в избранное
    fun toggleFavourite(productId: String) {
        viewModelScope.launch {
            try {
                val user = Constant.supabase.auth.currentUserOrNull()
                if (user != null) {
                    if (favourites.contains(productId)) {
                        // Удаляем товар из избранного
                        Constant.supabase.from("favourite")
                            .delete { "user_id = '${user.id}' AND product_id = '$productId'" } //конкретный товар тек польз

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
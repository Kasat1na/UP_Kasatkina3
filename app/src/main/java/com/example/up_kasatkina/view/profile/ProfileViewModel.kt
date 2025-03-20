package com.example.up_kasatkina.view.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.up_kasatkina.domain.Constant
import com.example.up_kasatkina.model.actions
import com.example.up_kasatkina.model.categories
import com.example.up_kasatkina.model.products
import com.example.up_kasatkina.model.profiles
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch


class ProfileViewModel: ViewModel() {
    var profiles by mutableStateOf<List<profiles>>(listOf())

    // Функция для загрузки профиля
    fun showProfile() {
        viewModelScope.launch {
            try {
                val user = Constant.supabase.auth.currentUserOrNull()
                if (user != null) {
                    profiles= Constant.supabase.from("profiles")
                        .select{
                            filter {
                                eq("user_id",user.id)

                            }
                        }.decodeList<profiles>()
                }
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }

    // Функция для обновления профиля текущего пользователя
    fun updateProfile(firstname: String, lastname: String, address: String, phone: String, photo: String) {
        viewModelScope.launch {
            try {
                val user = Constant.supabase.auth.currentUserOrNull()
                if (user != null) { // Проверяем, что пользователь вошел
                    Constant.supabase.from("profiles").update(
                        {
                            set("firstname", firstname)
                            set("lastname", lastname)
                            set("address", address)
                            set("phone", phone)
                        }
                    ) {
                        filter {
                            eq("user_id", user.id) // Обновляем только профиль текущего пользователя
                        }
                    }
                    showProfile() // Перезагружаем данные после обновления
                }
                Log.d("ProfileUpdate", "Профиль обновлен успешно")
            } catch (e: Exception) {
                Log.e("ProfileUpdateError", "Ошибка обновления профиля: ${e.message}")
            }
        }
    }
}

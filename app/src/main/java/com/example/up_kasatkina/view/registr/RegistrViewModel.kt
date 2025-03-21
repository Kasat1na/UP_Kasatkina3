package com.example.up_kasatkina.view.registr

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.up_kasatkina.domain.Constant
import com.example.up_kasatkina.model.profiles
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegistrViewModel: ViewModel() {
    var usname by mutableStateOf("")
    var uslogin by mutableStateOf("")
    var uspassword by mutableStateOf("")
    var emailError by mutableStateOf(false)
    // Регулярное выражение для проверки email
    private val emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    fun registr(navController: NavController, onError: (String) -> Unit) {
        if (!isValidEmail(uslogin)) {
            emailError = true
            onError("Некорректный email! Укажите email в формате name@domain.com")
            return
        }
        viewModelScope.launch {
            try {
                // Регистрация пользователя
                Constant.supabase.auth.signUpWith(Email) {
                    this.email = uslogin
                    this.password = uspassword
                }
                // Получаем ID только что зарегистрированного пользователя
                val userId = Constant.supabase.auth.currentUserOrNull()?.id
                    ?: throw Exception("Ошибка получения ID пользователя") // если нет то искл
                // Добавляем пользователя в таблицу profiles
                Constant.supabase.from("profiles").insert(
                    mapOf(
                        "id" to userId,
                        "user_id" to userId,
                        "firstname" to usname,
                        "lastname" to "",
                        "address" to "",
                        "phone" to "",
                        "photo" to ""
                    )
                )
                navController.navigate("Auth")
            } catch (e: Exception) {
                Log.d("err", e.message.toString())
                onError("Ошибка регистрации: ${e.message}")
            }
        }
    }
    private fun isValidEmail(email: String): Boolean {
        return emailPattern.matcher(email).matches()
    }
}
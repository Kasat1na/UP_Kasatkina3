package com.example.up_kasatkina.view.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.up_kasatkina.domain.Constant
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class AuthViewModel: ViewModel() {
    var uslogin by mutableStateOf("") //значение введенного email пользователя
    var uspassword by mutableStateOf("")
    var emailError by mutableStateOf(false) //для отслеживания, является ли email некорректным

    // Регулярное выражение для проверки email
    private val emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    fun auth(onError: (String) -> Unit) {
        if (!isValidEmail(uslogin)) {
            emailError = true
            onError("Некорректный email! Укажите email в формате name@domenname.ru")
            return
        }
        viewModelScope.launch {
            try {
                Constant.supabase.auth.signInWith(Email){
                    this.email = uslogin
                    this.password = uspassword
                }
            } catch (e: Exception) {
                Log.d("err", e.message.toString())
            }
        }
    }
//проверяет соответствует ли введенный почта регулярному выражению
    private fun isValidEmail(email: String): Boolean {
        return emailPattern.matcher(email).matches()
    }
}
package com.example.up_kasatkina.view.registr

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.up_kasatkina.domain.Constant
import com.example.up_kasatkina.model.profiles
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class RegistrViewModel: ViewModel() {
    var usname by mutableStateOf("")
    var uslogin by mutableStateOf("")
    var uspassword by mutableStateOf("")
    fun registr(){
        viewModelScope.launch {
            try{
                Constant.supabase.auth.signUpWith(Email){
                    this.email = uslogin
                    this.password = uspassword

                }
            }catch (e:Exception){
                Log.d("err", e.message.toString())
            }
        }
    }

}
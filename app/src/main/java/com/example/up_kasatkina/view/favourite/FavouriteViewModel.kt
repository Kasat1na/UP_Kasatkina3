package com.example.up_kasatkina.view.favourite

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.up_kasatkina.domain.Constant
import com.example.up_kasatkina.model.favourite
import com.example.up_kasatkina.model.profiles
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class FavouriteViewModel: ViewModel() {
    var favourite by mutableStateOf<List<favourite>>(listOf())

    fun showfavourite() {
        viewModelScope.launch {
            try {
                val user = Constant.supabase.auth.currentUserOrNull()
                if (user != null) {
                    favourite= Constant.supabase.from("favourite")
                        .select{
                            filter {
                                eq("user_id",user.id)

                            }
                        }.decodeList<favourite>()
                }
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }
}
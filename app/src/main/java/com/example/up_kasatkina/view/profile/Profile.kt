package com.example.up_kasatkina.view.profile


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.up_kasatkina.R
import com.example.up_kasatkina.model.profiles
import com.example.up_kasatkina.view.registr.RegistrViewModel
import java.time.format.TextStyle



@Composable
fun Profile(navController: NavController) {
    val vm = viewModel { ProfileViewModel() }
    var isEditing by remember { mutableStateOf(false) } // состояние для отслеживания режима редактирования
    vm.showProfile()
    // Состояния для каждого поля - хранят текущие данные юзер
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

// проверяем загружены ли данные профиля
    if (vm.profiles.isNotEmpty()) {
        val profile = vm.profiles.firstOrNull()

        // пересоздаём состояние при изменении режима редактирования
        LaunchedEffect(vm.profiles) {
            if (!isEditing && profile != null) {
                firstname = profile.firstname
                lastname = profile.lastname
                address = profile.address
                phone = profile.phone
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu",
                        modifier = Modifier.size(22.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Профиль", fontSize = 24.sp)
                }
                IconButton(onClick = {
                    if (isEditing) { // если был режим редактирования и нажали кнопку – сохраняем
                        profile?.let {
                            vm.updateProfile(
                                firstname,
                                lastname,
                                address,
                                phone,
                                ""
                            )
                        }
                    }
                    isEditing = !isEditing // переключаем состояние после сохранения
                }) {
                    if (isEditing) {
                        Text(
                            text = "Сохранить",
                            fontSize = 16.sp,
                            color = Color.Blue
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.editing),
                            contentDescription = "Редактировать",
                            modifier = Modifier.size(32.dp),
                                    tint = Color.Unspecified
                        )
                    }
                }
                }
            Spacer(modifier = Modifier.height(16.dp))

            // отображаем поля профиля
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(vm.profiles.firstOrNull()?.photo),
                    contentDescription = "Фото профиля",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .size(80.dp)
                        .clip(CircleShape)
                )
                // Имя
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Имя", fontSize = 14.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(6.dp))
                TextField(
                    value = firstname,
                    onValueChange = { if (isEditing) firstname = it },
                    label = { Text("Имя") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(247, 247, 249), RoundedCornerShape(16.dp)),
                    readOnly = !isEditing, // Режим редактирования
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(247, 247, 249, 255),
                        unfocusedContainerColor = Color(247, 247, 249, 255),
                        disabledContainerColor = Color(247, 247, 249, 255),
                        errorContainerColor = Color(247, 247, 249, 255),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
                // Фамилия
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Фамилия", fontSize = 14.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(6.dp))
                TextField(
                    value = lastname,
                    onValueChange = { if (isEditing) lastname = it },
                    label = { Text("Фамилия") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(247, 247, 249), RoundedCornerShape(16.dp)),
                    readOnly = !isEditing,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(247, 247, 249, 255),
                        unfocusedContainerColor = Color(247, 247, 249, 255),
                        disabledContainerColor = Color(247, 247, 249, 255),
                        errorContainerColor = Color(247, 247, 249, 255),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
                // Адрес
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Адрес", fontSize = 14.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(6.dp))
                TextField(
                    value = address,
                    onValueChange = { if (isEditing) address = it },
                    label = { Text("Адрес") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(247, 247, 249), RoundedCornerShape(16.dp)),
                    readOnly = !isEditing,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(247, 247, 249, 255),
                        unfocusedContainerColor = Color(247, 247, 249, 255),
                        disabledContainerColor = Color(247, 247, 249, 255),
                        errorContainerColor = Color(247, 247, 249, 255),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
                // Телефон
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Телефон", fontSize = 14.sp, color = Color.Black)
                Spacer(modifier = Modifier.height(6.dp))
                TextField(
                    value = phone,
                    onValueChange = { if (isEditing) phone = it },
                    label = { Text("Телефон") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(247, 247, 249), RoundedCornerShape(16.dp)),
                    readOnly = !isEditing,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(247, 247, 249, 255),
                        unfocusedContainerColor = Color(247, 247, 249, 255),
                        disabledContainerColor = Color(247, 247, 249, 255),
                        errorContainerColor = Color(247, 247, 249, 255),
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
    Column {
        Spacer(modifier = Modifier.weight(1f))
        com.example.up_kasatkina.view.home.BottomMenu(navController)
    }



}



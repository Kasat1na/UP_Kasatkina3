package com.example.up_kasatkina.view.auth


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.*

import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.up_kasatkina.R


//@Preview
@Composable
fun Auth(navController: NavController) {
    var passwordVisible by remember { mutableStateOf(false) } //видимость пароля
    val vm = viewModel { AuthViewModel() }
    var showDialog by remember { mutableStateOf(false) } // для отображения диалогового окна с ошибкой
    var errorMessage by remember { mutableStateOf("") } // для хранения сообщения об ошибке
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            shape = RoundedCornerShape(16.dp),
            containerColor = Color(247, 247, 249, 255),
            title = {
                Text(
                    text = "Ошибка",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            },
            text = {
                Text(
                    text = errorMessage,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF48B2E7)), // Голубая кнопка
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("ОК", color = Color.White, fontSize = 16.sp)
                }
            }
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(42.dp))
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(247, 247, 249, 255))
                .align(Alignment.Start),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {  }) {
                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "Back",
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Привет!", fontSize = 30.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Заполните Свои Данные", fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Email", fontSize = 14.sp, color = Color.Black, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = vm.uslogin,
            onValueChange = { vm.uslogin = it }, //обновляет значение почты в vm.uslogin
            isError = vm.emailError,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(247, 247, 249, 255), RoundedCornerShape(26.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(247, 247, 249, 255),
                unfocusedContainerColor = Color(247, 247, 249, 255),
                disabledContainerColor = Color(247, 247, 249, 255),
                errorContainerColor = Color(247, 247, 249, 255),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )

        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Пароль", fontSize = 14.sp, color = Color.Black, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = vm.uspassword,
            onValueChange = { vm.uspassword = it },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(247, 247, 249, 255), RoundedCornerShape(26.dp)),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye),
                        contentDescription = "Toggle password visibility",
                        modifier = Modifier.size(32.dp)
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(247, 247, 249, 255),
                unfocusedContainerColor = Color(247, 247, 249, 255),
                disabledContainerColor = Color(247, 247, 249, 255),
                errorContainerColor = Color(247, 247, 249, 255),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )


        )

        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Восстановить",
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                vm.auth { error -> // если ошибка то сообщение, если нет то навигация
                    errorMessage = error
                    showDialog = true
                }
                navController.navigate("Home")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
            .padding(vertical = 12.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF48B2E7))
        ) {
            Text(color = Color.White, text = "Войти", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(280.dp))
        TextButton(onClick = {navController.navigate("Registr") }) {
            Text(
                color = Color.Gray,
                text = "Вы впервые? ",
                fontWeight = FontWeight.Normal
            )
            Text(
                color = Color.Gray,
                text = "Создать",
                fontWeight = FontWeight.Bold
            )
        }

    }
}

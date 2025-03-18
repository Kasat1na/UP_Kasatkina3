package com.example.up_kasatkina.view.registr

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
fun Registr(navController: NavController) {
    var passwordVisible by remember { mutableStateOf(false) }
    var agreedToTerms by remember { mutableStateOf(false) }

    val vm = viewModel{ RegistrViewModel() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(247, 247, 249, 255))
                .align(Alignment.Start),
            contentAlignment = Alignment.Center // Центрируем иконку внутри круга
        ) {
            IconButton(onClick = { /* Handle navigation */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "Back",
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Регистрация", fontSize = 30.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Заполните Свои Данные", fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Ваше имя", fontSize = 14.sp, color = Color.Black, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = vm.usname,
            onValueChange = { vm.usname = it },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(247, 247, 249, 255), RoundedCornerShape(26.dp)), // Увеличен радиус
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
        Text(text = "Email", fontSize = 14.sp, color = Color.Black, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = vm.uslogin,
            onValueChange = { vm.uslogin = it },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Color(247, 247, 249, 255), RoundedCornerShape(26.dp)), // Увеличен радиус
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
                .background(Color(247, 247, 249, 255), RoundedCornerShape(26.dp)), // Увеличен радиус
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF48B2E7))
            )
            Text("Даю согласие на обработку персональных данных")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { vm.registr() },
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp) // Устанавливаем высоту кнопки
                .padding(vertical = 12.dp), // Добавляем вертикальные отступы для увеличения кнопки
            shape = RoundedCornerShape(16.dp), // Увеличиваем радиус закругления углов

            enabled = agreedToTerms,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (agreedToTerms) Color(0xFF48B2E7) else Color(43, 107, 139), // Темно-синий rgba(43, 107, 139, 1)
                disabledContainerColor = Color(43, 107, 139) // Явно указываем цвет для неактивного состояния
            )
        ) {
            Text(color = Color.White, text = "Зарегистрироваться", fontSize = 18.sp) // Увеличиваем шрифт для кнопки
        }

        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = { /* Navigate to login */ }) {
            Text(
                color = Color.Black,
                text = "Есть аккаунт? ",
                fontWeight = FontWeight.Normal // обычный текст для первой части
            )
            Text(
                color = Color.Black,
                text = "Войти",
                fontWeight = FontWeight.Bold // жирный текст для "Войти"
            )
        }

    }
}

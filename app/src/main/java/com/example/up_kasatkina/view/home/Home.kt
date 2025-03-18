package com.example.up_kasatkina.view.home


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.up_kasatkina.R
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import androidx.compose.material3.*



@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(247, 247, 249, 255)) // Фон rgba(247, 247, 249, 1)
    ) {
        Spacer(modifier = Modifier.height(42.dp))
        // Верхняя часть с иконками и текстом
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

                IconButton(onClick = { /* Handle navigation */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu), // Иконка меню
                        contentDescription = "Menu",
                        modifier = Modifier.size(22.dp)
                    )
                }


            // Текст "Главная" по центру
//            Spacer(modifier = Modifier.weight(1f)) // Отступ между иконкой и текстом
            Box(
                modifier = Modifier
                    .weight(1f) // Равномерно распределяет оставшееся пространство
                    .wrapContentWidth(Alignment.CenterHorizontally) // Центрирует по горизонтали
            ) {
                Text(text = "Главная", fontSize = 30.sp)
            }

            // Белый круг с иконкой "bag" справа
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { /* Handle bag click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.bag), // Иконка bag
                        contentDescription = "Bag",
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }

        // Поисковая строка с иконкой lupa
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            TextField(
                value = "", // Пример значения, сюда нужно добавить логику ввода
                onValueChange = { /* Handle text input */ },
                placeholder = {
                    Text(
                        text = "Поиск", // Меняем placeholder на "Поиск"
                        fontSize = 16.sp, // Увеличиваем размер шрифта placeholder
                        color = Color.Gray // Цвет placeholder
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.85f) // Уменьшаем ширину, чтобы оставить место для фильтра
                    .height(56.dp) // Увеличиваем высоту поля для лучшего отображения текста
                    .padding(horizontal = 14.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(12.dp), clip = false),
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.lupa), contentDescription = "Search", modifier = Modifier.size(17.dp))
                },
                shape = RoundedCornerShape(12.dp), // Скругленные углы для поля
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White, // Белый фон для активного состояния
                    unfocusedContainerColor = Color.White, // Белый фон для неактивного состояния
                    disabledContainerColor = Color.White, // Белый фон для отключенного состояния
                    unfocusedIndicatorColor = Color.Transparent, // Убираем индикатор неактивного состояния
                    focusedIndicatorColor = Color.Transparent // Убираем индикатор активного состояния
                ),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp, // Увеличиваем размер текста внутри поля
                    color = Color.Black // Цвет текста
                )
            )

            // Круг с цветом rgba(72, 178, 231, 1) и иконкой filter справа от поисковой строки
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color(72, 178, 231)),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { /* Handle filter click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter), // Иконка filter
                        contentDescription = "Filter",
                        modifier = Modifier.size(22.dp),
                                tint = Color.White
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))


        // Текст "Категории"
        Text(
            text = "Категории",
            fontSize = 19.sp,
            modifier = Modifier
                .padding(16.dp)
        )

        // Белый контейнер с категориями
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                CategoryBox("Все")
                CategoryBox("Outdoor")
                CategoryBox("Tennis")
            }
        }




        // Текст "Популярное" и "Все"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Популярное", fontSize = 19.sp)
            Text(text = "Все",color = Color(72, 178, 231) )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp) // Расстояние между карточками
        ) {
            ProductCard()
            ProductCard()
        }
        // Текст "Категории"
        Text(
            text = "Акции",
            fontSize = 19.sp,
            modifier = Modifier
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        BottomMenu()
    }
}
@Composable
fun BottomMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White) // Белый фон для меню
            .padding(vertical = 10.dp), // Паддинг сверху и снизу меню
        horizontalArrangement = Arrangement.SpaceEvenly, // Распределение иконок по ширине
        verticalAlignment = Alignment.CenterVertically // Вертикальное выравнивание иконок
    ) {
        // Иконка Home
        IconButton(onClick = { /* Обработка нажатия на Home */ }) {
            Icon(
                painter = painterResource(id = R.drawable.home), // Иконка "Home"
                contentDescription = "Home",
                modifier = Modifier.size(24.dp)
            )
        }

        // Иконка Like
        IconButton(onClick = { /* Обработка нажатия на Like */ }) {
            Icon(
                painter = painterResource(id = R.drawable.like), // Иконка "Like"
                contentDescription = "Like",
                modifier = Modifier.size(24.dp)
            )
        }

        // Голубой круг с иконкой Basket
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(72, 178, 231)), // Цвет голубого круга
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { /* Обработка нажатия на Basket */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.basket), // Иконка "Basket"
                    contentDescription = "Basket",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White // Белый цвет иконки
                )
            }
        }

        // Иконка Bell
        IconButton(onClick = { /* Обработка нажатия на Bell */ }) {
            Icon(
                painter = painterResource(id = R.drawable.bell), // Иконка "Bell"
                contentDescription = "Bell",
                modifier = Modifier.size(24.dp)
            )
        }

        // Иконка Profile
        IconButton(onClick = { /* Обработка нажатия на Profile */ }) {
            Icon(
                painter = painterResource(id = R.drawable.profile), // Иконка "Profile"
                contentDescription = "Profile",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun CategoryBox(text: String) {
    Box(
        modifier = Modifier
            .size(width = 110.dp, height = 45.dp) // Задаем фиксированные размеры
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White) // Серый фон
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = text, fontSize = 16.sp, color = Color.Gray, modifier = Modifier.align(Alignment.Center) )
    }
}


@Composable
fun ProductCard() {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .size(170.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Белый фон
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            // Серый круг с иконкой like в верхнем левом углу
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(Color(247, 247, 249, 255))
                    .align(Alignment.TopStart)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.like), // Иконка "like"
                    contentDescription = "Like",
                    modifier = Modifier.size(16.dp),
                    tint = Color.Black
                )
            }

            // Контент карточки
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("BEST SELLER", fontSize = 12.sp, color = Color.Blue)
                Text("Nike Air Max", fontWeight = FontWeight.Bold)
                Text("₱752.00", fontSize = 14.sp)
            }

            // Полукруг с иконкой basket в правом нижнем углу
            Box(
                modifier = Modifier
                    .size(width = 30.dp, height = 30.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp))
                    .background(Color(72, 178, 231))
                    .align(Alignment.BottomEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.basket), // Иконка "basket"
                    contentDescription = "Basket",
                    modifier = Modifier.size(13.dp),
                    tint = Color.White
                )
            }
        }
    }
}




@Preview
@Composable
fun PreviewHome() {
    Home()
}



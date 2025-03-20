package com.example.up_kasatkina.view.categoryProducts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.up_kasatkina.R
import com.example.up_kasatkina.view.home.HomeViewModel
import com.example.up_kasatkina.view.home.ProductCard

@Composable
fun CategoryProducts(navController: NavController, category: String) {
    val vm = viewModel { HomeViewModel() }
    var selectedCategory by remember { mutableStateOf(category) }

    LaunchedEffect(Unit) {
        vm.showcategories()
        vm.showproducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240)) // Серый фон
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Отступ сверху
        Spacer(modifier = Modifier.height(42.dp))

        // Круглая кнопка "Назад"
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(247, 247, 249, 255))
                .align(Alignment.Start),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "Назад",
                    modifier = Modifier.size(22.dp)
                )
            }
        }


        // Название категории
        Text(
            text = vm.categories.find { it.id == selectedCategory }?.title ?: "Категория",
            fontSize = 24.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Категории (горизонтальный список)
        LazyRow {
            items(vm.categories) { categories ->
                val isSelected = categories.id == selectedCategory
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .background(if (isSelected) Color(0xFF64B5F6) else Color.White, shape = MaterialTheme.shapes.medium)
                        .clickable {
                            selectedCategory = categories.id
                        }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = categories.title,
                        color = if (isSelected) Color.White else Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(vm.products.filter { it.category_id == selectedCategory }.chunked(2)) { rowProducts ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowProducts.forEach { product ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            ProductCard(product)
                        }
                    }
                    // Если в последней строке только 1 товар - заполняем пустое место
                    if (rowProducts.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
    Column {
        Spacer(modifier = Modifier.weight(1f)) // Works inside Column
        com.example.up_kasatkina.view.home.BottomMenu(navController)
    }
}


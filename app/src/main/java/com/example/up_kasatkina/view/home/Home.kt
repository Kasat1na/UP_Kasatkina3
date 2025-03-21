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
import androidx.compose.material.icons.filled.Star
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import coil.compose.rememberAsyncImagePainter
import com.example.up_kasatkina.model.products


@Composable
fun Home(navController: NavController) {
    val vm = viewModel{HomeViewModel()}
    LaunchedEffect(Unit) {
        vm.showproducts()
        vm.showactions()
        vm.showcategories()
        vm.checkIfFavourite()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(247, 247, 249, 255))
    ) {
        Spacer(modifier = Modifier.height(42.dp))
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
                Text(text = "Главная", fontSize = 30.sp)
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(id = R.drawable.bag),
                        contentDescription = "Bag",
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
        // Поиск
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            TextField(
                value = vm.searchQuery,
                onValueChange = { vm.updateSearchQuery(it) }, // обновляет запрос в ViewModel
                placeholder = { Text("Поиск", fontSize = 16.sp, color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(56.dp)
                    .padding(horizontal = 14.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(12.dp)),
                leadingIcon = {
                    Icon(painter = painterResource(id = R.drawable.lupa), contentDescription = "Search", modifier = Modifier.size(17.dp))
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp, color = Color.Black)
            )
            Box(
                modifier = Modifier.size(50.dp).clip(CircleShape).background(Color(72, 178, 231)),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { /* Фильтр */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "Filter",
                        modifier = Modifier.size(22.dp),
                        tint = Color.White
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Категории
        Text("Категории", fontSize = 19.sp, modifier = Modifier.padding(16.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(vm.categories) { category ->
                CategoryBox(category.title) {
                    navController.navigate("category_products/${category.id}") // используем category.id и навигация по выбранной категории
                }
            }

        }
        // Популярное или результаты поиска
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //Если searchQuery пустой то
            Text(text = if (vm.searchQuery.isEmpty()) "Популярное" else "Результаты поиска", fontSize = 19.sp)
            if (vm.searchQuery.isEmpty()) Text(text = "Все", color = Color(72, 178, 231))
        }
        //две карточки
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(if (vm.searchQuery.isEmpty()) vm.products.take(2) else vm.filteredProducts) { product ->
                ProductCard(product)
            }
        }
        Text(
            text = "Акции",
            fontSize = 19.sp,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(
                vm.actions,
                key = { actions -> actions.id }
            ) { actions ->
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(actions.photo),
                        contentDescription = "",
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        BottomMenu(navController)
    }
}
@Composable
fun BottomMenu(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .background(Color.White)
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {navController.navigate("Home") }) {
            Icon(
                painter = painterResource(id = R.drawable.home), // Иконка "Home"
                contentDescription = "Home",
                modifier = Modifier.size(24.dp)
            )
        }
        IconButton(onClick = {navController.navigate("Favourite") }) {
            Icon(
                painter = painterResource(id = R.drawable.like), // Иконка "Like"
                contentDescription = "Like",
                modifier = Modifier.size(24.dp)
            )
        }
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color(72, 178, 231)),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {  }) {
                Icon(
                    painter = painterResource(id = R.drawable.basket), // Иконка "Basket"
                    contentDescription = "Basket",
                    modifier = Modifier.size(24.dp),
                    tint = Color.White
                )
            }
        }
        IconButton(onClick = {  }) {
            Icon(
                painter = painterResource(id = R.drawable.bell), // Иконка "Bell"
                contentDescription = "Bell",
                modifier = Modifier.size(24.dp)
            )
        }
        IconButton(onClick = {navController.navigate("Profile") }) {
            Icon(
                painter = painterResource(id = R.drawable.profile), // Иконка "Profile"
                contentDescription = "Profile",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun CategoryBox(categoryTitle: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 110.dp, height = 45.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .clickable(onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = categoryTitle)
    }
}
@Composable
fun ProductCard(product: products) {
    val vm = viewModel{ HomeViewModel() }
    LaunchedEffect(vm.favourites) {
        vm.checkIfFavourite() // обновляем избранное при изменении
    }
    val isLiked = vm.favourites.contains(product.id) // проверяем в избранном ли товар

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .size(180.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Box(
                modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(Color(247, 247, 249, 255))
                    .align(Alignment.TopStart)
                    .padding(4.dp)
                    .clickable { vm.toggleFavourite(product.id) }, // Изменяем избранное
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = if (isLiked) R.drawable.like2 else R.drawable.like),
                    contentDescription = "Like",
                    modifier = Modifier.size(16.dp),
                    tint = if (isLiked) Color.Red else Color.Black // Красное, если в избранном
                )
            }

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    painter = rememberAsyncImagePainter(product.image),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .height(80.dp)
                        .width(80.dp)
                )
                Text(
                    text = product.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "${product.cost} ₽",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

            }
            Box(
                modifier = Modifier
                    .size(width = 30.dp, height = 30.dp)
                    .clip(RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp))
                    .background(Color(72, 178, 231))
                    .align(Alignment.BottomEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.basket),
                    contentDescription = "Basket",
                    modifier = Modifier.size(13.dp),
                    tint = Color.White
                )
            }
        }
    }
}











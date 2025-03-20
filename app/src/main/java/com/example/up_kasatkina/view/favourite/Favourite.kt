package com.example.up_kasatkina.view.favourite
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
fun Favourite(navController: NavController) {
    val vm: FavouriteViewModel = viewModel()
    val homeVm: HomeViewModel = viewModel() // Добавляем доступ к товарам

    LaunchedEffect(Unit) {
        vm.showfavourite()
        homeVm.showproducts() // Загружаем все продукты
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(240, 240, 240))
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
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.circle),
                    contentDescription = "Назад",
                    modifier = Modifier.size(22.dp)
                )
            }
        }

        Text(
            text = "Избранное",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        val favouriteProducts = homeVm.products.filter { product ->
            vm.favourite.any { it.product_id == product.id } // Сопоставляем избранное с товарами
        }

        if (favouriteProducts.isEmpty()) {
            Text(
                text = "Ваш список избранного пуст",
                fontSize = 18.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 32.dp)
            )
        } else {
            LazyColumn {
                items(favouriteProducts.chunked(2)) { rowProducts ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowProducts.forEach { product ->
                            Box(
                                modifier = Modifier.weight(1f)
                            ) {
                                ProductCard(product)
                            }
                        }
                        if (rowProducts.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
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

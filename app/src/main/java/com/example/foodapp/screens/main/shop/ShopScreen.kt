package com.example.foodapp.screens.main.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.foodapp.data.FoodModelDb
import com.example.foodapp.storage.DbHandler
import com.example.foodapp.ui.theme.GrayLite2

@Composable
fun ShopScreen(navController: NavHostController) {

    val db = DbHandler(LocalContext.current)
    val foodList: MutableList<FoodModelDb> = db.readFoods()!!

    Column(modifier = Modifier.fillMaxSize().background(color = GrayLite2)) {
        LazyColumn(
            modifier = Modifier.background(color = GrayLite2),
            content = {
                itemsIndexed(foodList) { index, item ->
                    ItemFoodOrder(item = item)
                }
            })
    }
}
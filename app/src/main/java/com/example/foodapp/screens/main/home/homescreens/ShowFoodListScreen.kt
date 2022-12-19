package com.example.foodapp.screens.main.home.homescreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodapp.data.FoodModel
import com.example.foodapp.screens.main.home.ItemFood

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowFoodList(list: List<FoodModel>, navControllerHome: NavHostController) {

    LazyVerticalGrid(
        modifier = Modifier.padding(top = 10.dp),
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        items(list.size) { index ->
            ItemFood(item = list[index], navControllerHome = navControllerHome)
        }
    }
}
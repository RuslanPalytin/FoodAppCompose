package com.example.foodapp.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodapp.data.FoodModel
import com.example.foodapp.navigation.ItemFoodScreen
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange
import com.example.foodapp.ui.theme.Roboto

@Composable
fun ItemFood(item: FoodModel, navController: NavHostController) {
    Box(
        modifier = Modifier
            .height(270.dp)
            .width(150.dp)
            .background(color = Color.Transparent)
            .clickable {
                navController.navigate(ItemFoodScreen.SelectedItemFood.route)
            }
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 45.dp, bottom = 15.dp),
            shape = RoundedCornerShape(24.dp),
            backgroundColor = Color.White,
            elevation = 20.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(90.dp))
                Text(
                    text = item.name,
                    fontSize = 22.sp,
                    fontFamily = NutinoRegular,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(text = item.price, fontSize = 17.sp, fontFamily = Roboto, color = Orange)
            }
        }
        Card(
            modifier = Modifier
                .clip(shape = CircleShape)
                .align(Alignment.TopCenter)
                .size(130.dp),
            backgroundColor = Color.White,
            elevation = 70.dp
        ) {
            AsyncImage(
                model = item.icon,
                contentDescription = null,
            )
        }
    }
}
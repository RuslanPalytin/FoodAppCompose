package com.example.foodapp.screens.main.history

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foodapp.data.FoodModelDb
import com.example.foodapp.data.OrderGetModel
import com.example.foodapp.graphs.Graph
import com.example.foodapp.navigation.BottomBarScreen
import com.example.foodapp.storage.DbHandler
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange

@Composable
fun HistoryOpenScreen(
    navController: NavHostController,
    items: List<OrderGetModel>,
    position: Int,
    addressAndDate: String,
    totalPrice: String,
    mutableAddressAndDate: MutableState<String>,
) {
    mutableAddressAndDate.value = addressAndDate
    val context = LocalContext.current
    val listFood: MutableList<FoodModelDb> = mutableListOf()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            content = {
                itemsIndexed(items[position].dishes) { _, item ->
                    ItemOpenHistory(item = item)
                    listFood.add(
                        FoodModelDb(
                            id = item.dish.id,
                            icon = item.dish.icon,
                            name = item.dish.name,
                            price = item.dish.price,
                            count = item.count.toInt()
                        )
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
            shape = RoundedCornerShape(30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Order Price",
                    color = Color.White,
                    fontFamily = NutinoRegular,
                    fontSize = 17.sp
                )
                Text(
                    text = "$totalPrice$$",
                    color = Color.White,
                    fontFamily = NutinoRegular,
                    fontSize = 17.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {

                repeatSaveFood(
                    navController = navController,
                    context = context,
                    items = listFood
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 10.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = "Repeat Order",
                color = Color.White,
                fontFamily = NutinoRegular,
                fontSize = 17.sp
            )
        }
    }
}

fun repeatSaveFood(
    navController: NavHostController,
    context: Context,
    items: MutableList<FoodModelDb>
) {
    val db = DbHandler(context)
    db.addAllNewFood(items)

    navController.navigate(Graph.HOME)
    navController.navigate(BottomBarScreen.Shop.route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}
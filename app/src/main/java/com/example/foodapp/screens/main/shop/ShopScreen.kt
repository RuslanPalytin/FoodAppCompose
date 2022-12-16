package com.example.foodapp.screens.main.shop

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foodapp.data.FoodModelDb
import com.example.foodapp.storage.DbHandler
import com.example.foodapp.R
import com.example.foodapp.data.DishesModel
import com.example.foodapp.data.OrderModel
import com.example.foodapp.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ShopScreen(navController: NavHostController) {

    val context = LocalContext.current
    val db = DbHandler(context)
    val foodList: MutableList<FoodModelDb> = db.readFoods()!!
    var address by remember { mutableStateOf("") }
    val defaultText = "ул. Большевитская, 68/1"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLite2)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Order", fontSize = 48.sp, fontFamily = Italiano)
            TextField(
                value = address, onValueChange = { newText -> address = newText },
                placeholder = {
                    Text(
                        text = defaultText,
                        fontSize = 16.sp,
                        fontFamily = Roboto
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = GrayLite2,
                    textColor = Color.Black,
                    focusedIndicatorColor = GrayLite2,
                    unfocusedIndicatorColor = GrayLite2,
                    cursorColor = Color.Gray
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_navigate),
                contentDescription = null,
                tint = Color.Gray
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .background(color = GrayLite2),
            content = {
                itemsIndexed(foodList) { index, item ->
                    ItemFoodOrder(item = item)
                }
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Дополнительно", fontSize = 22.sp, modifier = Modifier.padding(start = 10.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = GrayLite2),
            content = {
                items(10) {
                    ItemAdditionally()
                }
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        OrderPrice()
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                onClickOrderNow(
                    foodList = foodList,
                    address = if(address != "") address else defaultText,
                    context = context
                )
                db.deleteAllFoods(items = foodList)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .height(70.dp),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(
                text = "Order Now",
                fontFamily = NutinoRegular,
                fontSize = 17.sp,
                color = Color.White
            )
        }

    }
}

@SuppressLint("SimpleDateFormat")
fun onClickOrderNow(foodList: MutableList<FoodModelDb>, address: String, context: Context) {

    val dishesList: MutableList<DishesModel> = mutableListOf()

    for(i in 0 until foodList.size) {
        dishesList.add(
            DishesModel(
                id = foodList[i].id,
                count = foodList[i].count.toString()
        )
        )
    }

    val unixTime: Int = (Date().time / 1000).toInt()
    val date = SimpleDateFormat("dd.MM.yyyy HH:mm")
    date.timeZone = TimeZone.getTimeZone("Europe/Moscow")
    val orderModel = OrderModel(
        address = address,
        date = date.format(unixTime * 1000L),
        dishes = dishesList
    )
    postFoods(orderModel, context)
}

@Composable
fun OrderPrice() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.dp, color = Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .background(color = OrangeOrder)
                .fillMaxWidth()
                .height(40.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Order Price", color = Color.White, fontFamily = NutinoRegular)
            Text(text = "20000$$", color = Color.White, fontFamily = NutinoRegular)
        }
    }
}
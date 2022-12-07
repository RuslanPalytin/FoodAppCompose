package com.example.foodapp.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.example.foodapp.data.FoodModel
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.NutinoRegular

@Composable
fun SearchResult(search: MutableState<String>) {

    val response = remember { mutableStateOf<List<FoodModel>?>(null) }
    val context = LocalContext.current
    val listFood: MutableList<FoodModel> = mutableListOf()
    getListFoods(context = context, result = response)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLite1)
    ) {
        if (response.value != null)
            for (i in 0 until response.value!!.size) {
                if (search.value == response.value!![i].name) {
                    listFood.add(response.value!![i])
                }
            }

        if (listFood.isNotEmpty()) {
            ShowFoodList(list = listFood)
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Empty", fontSize = 40.sp, fontFamily = NutinoRegular)
            }
        }
    }
}
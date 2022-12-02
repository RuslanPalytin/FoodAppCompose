package com.example.foodapp.screens.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodapp.api.ApiService
import com.example.foodapp.data.FoodModel
import com.example.foodapp.storage.SharedPreference
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.Orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen() {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(GrayLite1)
    var search by remember { mutableStateOf("") }
    var listFood: List<FoodModel> by remember { mutableStateOf(listOf()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLite1)
            .padding(horizontal = 5.dp)
            .padding(top = 5.dp, bottom = 55.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = CenterVertically) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.7f),
                value = search,
                onValueChange = { newText -> search = newText },
                placeholder = {
                    Text(
                        text = "Выберите адрес доставки",
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = GrayLite1,
                    focusedIndicatorColor = GrayLite1,
                    unfocusedIndicatorColor = GrayLite1
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier
                        .align(CenterVertically)
                        .size(40.dp),
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .align(CenterVertically)
                        .size(40.dp),
                    tint = Color.Gray
                )
            }
        }

        listFood = getListFoods(context = context)

        LazyVerticalGrid(
            modifier = Modifier.padding(top = 10.dp),
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            content = {
                itemsIndexed(items = listFood) { index, item ->
                    ItemFood(item)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun ItemFood(item: FoodModel) {
    Box(
        modifier = Modifier
            .height(270.dp)
            .width(150.dp)
            .background(color = Color.Transparent)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 45.dp, bottom = 15.dp),
            shape = RoundedCornerShape(24.dp),
            backgroundColor = Color.White,
            elevation = 20.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) {
                Spacer(modifier = Modifier.height(100.dp))
                Text(text = item.name, fontSize = 20.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = item.price, color = Orange)
            }
        }
        Box(modifier = Modifier
            .clip(shape = CircleShape)
            .align(TopCenter)
            .size(130.dp)
        ) {
            AsyncImage(
                model = item.icon,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun getListFoods(context: Context): List<FoodModel> {

    var foodList: List<FoodModel> by remember { mutableStateOf(listOf()) }
    val token = SharedPreference(context)

    ApiService.retrofit.getFoods("Bearer ${token.readToken()}", "1.0")
        .enqueue(object : Callback<List<FoodModel>> {
            override fun onResponse(
                call: Call<List<FoodModel>>,
                response: Response<List<FoodModel>>
            ) {
                if (response.isSuccessful) {
                    foodList = response.body()!!
                } else {
                    Toast.makeText(context, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<FoodModel>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    return foodList
}
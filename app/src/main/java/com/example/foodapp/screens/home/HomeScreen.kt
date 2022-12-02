package com.example.foodapp.screens.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodapp.R
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
    val foodList = remember { mutableStateOf<List<FoodModel>?>(null)}
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

        //Log.d("MyLog", getListFoods(context).toString())
        val token = SharedPreference(context)

        Log.d("MyLog", token.readToken())

        foodList.value = getListFoods(context = context).value
        Log.d("MyLog", getListFoods(context = context).value.toString())


        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            content = {
                items(foodList.value!!.size){ item ->
                    ItemFood(foodList.value!!, item)
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
fun ItemFood(item: List<FoodModel>, position: Int) {
    Box(
        modifier = Modifier
            .height(270.dp)
            .width(150.dp)
            .background(color = Color.Transparent)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            shape = RoundedCornerShape(24.dp),
            backgroundColor = Color.White,
            elevation = 3.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) {
                Spacer(modifier = Modifier.height(100.dp))
                Text(text = item[position].name, fontSize = 20.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = item[position].price, color = Orange)
            }
        }

        AsyncImage(
            model = item[position].icon,
            contentDescription = null,
            modifier = Modifier
                .clip(shape = CircleShape)
                .align(TopCenter)
                .size(130.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemFoodPreview() {
    //ItemFood()
}

@Composable
fun getListFoods(context: Context): MutableState<List<FoodModel>?>{

    val _foodList = remember { mutableStateOf<List<FoodModel>?>(null)}
    //var foodList = _foodList.value
    val token = SharedPreference(context)

    //Log.d("MyLog", token.readToken())

    ApiService.retrofit.getFoods("Bearer ${token.readToken()}", "1.0").enqueue(object: Callback<List<FoodModel>>{
        override fun onResponse(call: Call<List<FoodModel>>, response: Response<List<FoodModel>>) {
            if(response.isSuccessful) {
                _foodList.value = response.body()
                Log.d("MyLog", _foodList.value.toString())
            }else {
                Log.d("MyLog", "Error ${response.code()}")
                Toast.makeText(context, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<List<FoodModel>>, t: Throwable) {
            Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
        }
    })
    return _foodList
}
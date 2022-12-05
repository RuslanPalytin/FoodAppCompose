package com.example.foodapp.screens.main.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange
import com.example.foodapp.ui.theme.Roboto
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(GrayLite1)

    var search by remember { mutableStateOf("") }
    val context = LocalContext.current
    var _foodList: List<FoodModel> by remember { mutableStateOf(listOf()) }
    var visibilityIcons by remember { mutableStateOf(true) }
    val foodList = _foodList
    var _category: List<FoodModel> by remember { mutableStateOf(listOf()) }
    val category = _category
    val pagerState = rememberPagerState()

    _foodList = getListFoods(context = context)
    _category = _foodList.distinctBy { it.category }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLite1)
            .padding(horizontal = 5.dp)
            .padding(top = 25.dp, bottom = 55.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clip(shape = RoundedCornerShape(30.dp))
                .background(color = if (visibilityIcons) GrayLite1 else Color.White),
            verticalAlignment = CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.75f),
                value = search,
                onValueChange = { newText -> search = newText },
                placeholder = {
                    Text(
                        text = if(visibilityIcons) "Выберите адрес доставки" else "Search",
                        color = Color.Gray,
                        fontFamily = Roboto,
                        fontSize = 18.sp
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = if (visibilityIcons) GrayLite1 else Color.White,
                    focusedIndicatorColor = if (visibilityIcons) GrayLite1 else Color.White,
                    unfocusedIndicatorColor = if (visibilityIcons) GrayLite1 else Color.White
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                AnimatedVisibility(visible = visibilityIcons) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_navigate),
                        contentDescription = null,
                        modifier = Modifier
                            .align(CenterVertically)
                            .size(24.dp),
                        tint = Color.Gray,
                    )
                }
                AnimatedVisibility(visible = !visibilityIcons) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null,
                        modifier = Modifier
                            .align(CenterVertically)
                            .size(34.dp),
                        tint = Color.Gray
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier
                        .align(CenterVertically)
                        .size(24.dp)
                        .clickable {
                            visibilityIcons = !visibilityIcons
                        },
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(25.dp))
            }
        }

        LazyVerticalGrid(
            modifier = Modifier.padding(top = 10.dp),
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            content = {
                itemsIndexed(items = foodList) { index, item ->
                    ItemFood(item)
                }
            }
        )

        Spacer(modifier = Modifier.width(30.dp))

        Scaffold(
            modifier = Modifier.fillMaxWidth()
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                //Tabs(tabs = category, pagerState = pagerState)
                //TabsContent(tabs = category, pagerState = pagerState)
            }
        }
    }
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
                .align(TopCenter)
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

@Composable
fun getListFoods(context: Context): List<FoodModel> {

    var _foodList: List<FoodModel> by remember { mutableStateOf(listOf()) }
    var foodList = _foodList

    //var foodList: List<FoodModel> by remember { mutableStateOf(listOf()) }
    val token = SharedPreference(context)

    ApiService.retrofit.getFoods("Bearer ${token.readToken()}", "1.0")
        .enqueue(object : Callback<List<FoodModel>> {
            override fun onResponse(
                call: Call<List<FoodModel>>,
                response: Response<List<FoodModel>>
            ) {
                if (response.isSuccessful) {
                    Log.d("MyLog", "Response:" + response.body()!!.toString())
                    _foodList = response.body()!!
                } else {
                    Toast.makeText(context, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<FoodModel>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })

    Log.d("MyLog", "getlist: $_foodList")
    Log.d("MyLog", "getlist2: $foodList")

    return foodList
}
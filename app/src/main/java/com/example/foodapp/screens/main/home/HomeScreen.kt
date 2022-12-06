package com.example.foodapp.screens.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodapp.data.FoodModel
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange
import com.example.foodapp.ui.theme.Roboto
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(GrayLite1)

    val context = LocalContext.current

    val search = remember { mutableStateOf("") }
    val response = remember { mutableStateOf<List<FoodModel>?>(null) }
    val pagerState = rememberPagerState()

    getListFoods(context = context, result = response)
    val category = response.value?.distinctBy { it.category }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLite1)
            .padding(horizontal = 5.dp)
            .padding(top = 25.dp, bottom = 55.dp)
    ) {
        Search(search)

        if (response.value != null) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    Tabs(tabs = category!!, pagerState = pagerState, search = search)
                    TabsContent(tabs = category, response = response, pagerState = pagerState)
                }
            }
        }

        Spacer(modifier = Modifier.width(30.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowFoodList(list: List<FoodModel>) {
    LazyVerticalGrid(
        modifier = Modifier.padding(top = 10.dp),
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        items(list.size) { index ->
            ItemFood(item = list[index])
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
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = CenterHorizontally
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
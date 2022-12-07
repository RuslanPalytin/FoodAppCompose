package com.example.foodapp.screens.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.foodapp.data.FoodModel
import com.example.foodapp.ui.theme.GrayLite1
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
    val isVisible = remember { mutableStateOf(true) }
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
        Search(search = search, isVisible = isVisible)

        SelectedItemScreen()

        if (response.value != null) {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { padding ->
                Column(modifier = Modifier.padding(padding)) {
                    Tabs(
                        tabs = category!!,
                        pagerState = pagerState,
                        search = search,
                        isVisible = isVisible
                    )
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
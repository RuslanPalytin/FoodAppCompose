package com.example.foodapp.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.foodapp.data.FoodModel
import com.example.foodapp.ui.theme.GrayLite1
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
    tabs: List<FoodModel>,
    response: MutableState<List<FoodModel>?>,
    pagerState: PagerState
) {
    HorizontalPager(
        count = tabs.size,
        state = pagerState,
        modifier = Modifier.fillMaxSize().background(color = GrayLite1)
    ) { page ->

        val list: MutableList<FoodModel> = mutableListOf()

        for(i in 0 until response.value!!.size){
            if(response.value!![i].category == tabs[page].category){
                list.add(response.value!![i])
            }
        }
        ShowFoodList(list = list)
    }
}
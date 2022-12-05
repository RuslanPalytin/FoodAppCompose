package com.example.foodapp.screens.main.home

import androidx.compose.runtime.Composable
import com.example.foodapp.data.FoodModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<FoodModel>, pagerState: PagerState) {
    HorizontalPager(count = tabs.size, state = pagerState) { page ->
        tabs[page].category
    }
}
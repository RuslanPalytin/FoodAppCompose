package com.example.foodapp.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.foodapp.ui.theme.Orange
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoarding(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(Orange)

    val pagerState = rememberPagerState()

    HorizontalPager(
        state = pagerState,
        count = 2,
        modifier = Modifier
            .fillMaxSize()
            .background(Orange)
    ) { page ->
        when(page) {
            0 -> OnBoardingScreen1()
            1 -> OnBoardingScreen2(navController = navController)
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(bottom = 50.dp),
            activeColor = Color.White,
            inactiveColor = Color.Gray,
            indicatorHeight = 10.dp,
            indicatorWidth = 10.dp
        )
    }
}
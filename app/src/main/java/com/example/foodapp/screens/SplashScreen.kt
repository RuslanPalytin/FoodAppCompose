package com.example.foodapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.R
import com.example.foodapp.graphs.Graph
import com.example.foodapp.navigation.BottomBarScreen
import com.example.foodapp.navigation.OnBoardingScreen
import com.example.foodapp.storage.SharedPreference
import com.example.foodapp.ui.theme.Italiano
import com.example.foodapp.ui.theme.Orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    val systemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false
    val token = SharedPreference(LocalContext.current)

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_launch_screen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .size(450.dp),
            painter = painterResource(id = R.drawable.elipse_launch_screen),
            contentDescription = null,
            alpha = 0.6f
        )
        Column(
            modifier = Modifier
                .align(Center)
                .padding(bottom = 70.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cooking_launch_screen),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "WSR Food",
                modifier = Modifier.align(CenterHorizontally),
                fontSize = 50.sp,
                fontFamily = Italiano,
            )
            Spacer(modifier = Modifier.height(10.dp))
            CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally), color = Orange)
        }

    }
    LaunchedEffect(key1 = true) {
        delay(1000)
        if(token.readToken() == ""){
            navController.navigate(OnBoardingScreen.OnBoarding.route)
        } else {
            navController.navigate(Graph.HOME)
        }
    }
}
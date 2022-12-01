package com.example.foodapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.foodapp.R
import com.example.foodapp.navigation.Screen
import com.example.foodapp.ui.theme.Italiano
import com.example.foodapp.ui.theme.Orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun OnBoardingScreen2(navController: NavHostController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.isStatusBarVisible = false

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Orange),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.on_boarding_2),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Button(
                onClick = { navController.navigate(Screen.LoginScreen.route) },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(70.dp)
                    .padding(start = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(text = "Sign In", fontFamily = Italiano, fontSize = 35.sp)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = { navController.navigate(Screen.RegisterScreen.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(end = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text(text = "Sign Up", fontFamily = Italiano, fontSize = 35.sp)
            }
        }
    }
}
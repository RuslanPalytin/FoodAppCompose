package com.example.foodapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.screens.*

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.SplashScreen.route){
        composable(Screen.SplashScreen.route){
            LaunchScreen(navController)
        }
        composable(Screen.OnBoarding.route){
            OnBoarding(navController)
        }
        composable(Screen.OnBoardingScreen2.route){
            OnBoardingScreen2(navController)
        }
        composable(Screen.OnBoardingScreen1.route){
            OnBoardingScreen1()
        }
        composable(Screen.RegisterScreen.route){
            RegisterScreen(navController)
        }
        composable(Screen.LoginScreen.route){
            LoginScreen(navController)
        }
    }
}
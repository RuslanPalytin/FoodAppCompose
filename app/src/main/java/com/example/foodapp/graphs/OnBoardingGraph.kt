package com.example.foodapp.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodapp.navigation.OnBoardingScreen
import com.example.foodapp.screens.SplashScreen
import com.example.foodapp.screens.authorization.LoginScreen
import com.example.foodapp.screens.onboarding.OnBoarding
import com.example.foodapp.screens.onboarding.OnBoardingScreen1
import com.example.foodapp.screens.onboarding.OnBoardingScreen2
import com.example.foodapp.screens.authorization.RegisterScreen

fun NavGraphBuilder.onBoardingGraph(navController: NavHostController) {
    navigation(route = Graph.ON_BOARDING, startDestination = OnBoardingScreen.SplashScreen.route) {

        composable(OnBoardingScreen.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(OnBoardingScreen.OnBoarding.route) {
            OnBoarding(navController)
        }
        composable(OnBoardingScreen.OnBoardingScreen1.route) {
            OnBoardingScreen1()
        }
        composable(OnBoardingScreen.OnBoardingScreen2.route) {
            OnBoardingScreen2(navController)
        }
        composable(OnBoardingScreen.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(OnBoardingScreen.LoginScreen.route) {
            LoginScreen(navController)
        }

    }
}
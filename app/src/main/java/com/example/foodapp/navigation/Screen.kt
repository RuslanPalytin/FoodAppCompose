package com.example.foodapp.navigation

sealed class Screen(val route: String) {
    object SplashScreen: Screen("splash")
    object OnBoarding: Screen("on_boarding")
    object OnBoardingScreen1: Screen("on_boarding_1")
    object OnBoardingScreen2: Screen("on_boarding_2")
    object RegisterScreen: Screen("register")
    object LoginScreen: Screen("login")
}

package com.example.foodapp.navigation

sealed class OnBoardingScreen(val route: String) {
    object SplashScreen: OnBoardingScreen("splash")
    object OnBoarding: OnBoardingScreen("on_boarding")
    object OnBoardingScreen1: OnBoardingScreen("on_boarding_1")
    object OnBoardingScreen2: OnBoardingScreen("on_boarding_2")
    object RegisterScreen: OnBoardingScreen("register")
    object LoginScreen: OnBoardingScreen("login")
}

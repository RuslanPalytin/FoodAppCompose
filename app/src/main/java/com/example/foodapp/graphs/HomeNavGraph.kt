package com.example.foodapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodapp.navigation.BottomBarScreen
import com.example.foodapp.screens.home.HistoryScreen
import com.example.foodapp.screens.home.HomeScreen
import com.example.foodapp.screens.home.PersonScreen
import com.example.foodapp.screens.home.ShopScreen

@Composable
fun HomeNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Shop.route){
            ShopScreen()
        }
        composable(route = BottomBarScreen.Person.route){
            PersonScreen()
        }
        composable(route = BottomBarScreen.History.route){
            HistoryScreen()
        }
    }
}
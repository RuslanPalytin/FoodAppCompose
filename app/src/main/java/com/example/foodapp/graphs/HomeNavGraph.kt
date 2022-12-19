package com.example.foodapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.foodapp.data.FoodModel
import com.example.foodapp.navigation.*
import com.example.foodapp.screens.main.history.HistoryScreen
import com.example.foodapp.screens.main.home.*
import com.example.foodapp.screens.main.person.PersonScreen
import com.example.foodapp.screens.main.shop.ShopScreen

@Composable
fun HomeNavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.Shop.route) {
            ShopScreen(navController)
        }
        composable(route = BottomBarScreen.Person.route) {
            PersonScreen(navController)
        }
        composable(route = BottomBarScreen.History.route) {
            HistoryScreen(navController)
        }
        composable(ItemFoodScreen.OneItem.route) {
            OneItemScreen(navController)
        }
    }
}
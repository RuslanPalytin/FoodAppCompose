package com.example.foodapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodapp.data.FoodModel
import com.example.foodapp.navigation.BottomBarScreen
import com.example.foodapp.navigation.ItemFoodScreen
import com.example.foodapp.screens.main.history.HistoryScreen
import com.example.foodapp.screens.main.home.AddToCardScreen
import com.example.foodapp.screens.main.home.HomeScreen
import com.example.foodapp.screens.main.home.ItemFood
import com.example.foodapp.screens.main.home.SelectedItemScreen
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
            ShopScreen()
        }
        composable(route = BottomBarScreen.Person.route) {
            PersonScreen()
        }
        composable(route = BottomBarScreen.History.route) {
            HistoryScreen()
        }
        composable(route = ItemFoodScreen.ItemFood.route) {
            ItemFood(item = FoodModel("", "", "", "", "", ""), navController)
        }
        composable(route = ItemFoodScreen.SelectedItemFood.route){
            SelectedItemScreen(navController)
        }
        composable(route = ItemFoodScreen.AddToCartItemFood.route){
            AddToCardScreen()
        }
    }
}

fun NavGraphBuilder.itemFoodGraph(navController: NavHostController) {
    navigation(route = Graph.ITEM, startDestination = ItemFoodScreen.ItemFood.route) {

        composable(route = ItemFoodScreen.ItemFood.route) {
            ItemFood(item = FoodModel("", "", "", "", "", ""), navController)
        }

        composable(route = ItemFoodScreen.SelectedItemFood.route) {
            //SelectedItemScreen(FoodModel("", "", "", "", "", ""), navController)
        }
    }
}
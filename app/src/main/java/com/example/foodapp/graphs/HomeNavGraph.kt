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
            ShopScreen(navController)
        }
        composable(route = BottomBarScreen.Person.route) {
            PersonScreen(navController)
        }
        composable(route = BottomBarScreen.History.route) {
            HistoryScreen(navController)
        }
        composable(route = ItemFoodScreen.ItemFood.route) {
            ItemFood(item = FoodModel("", "", "", "", "", ""), navController)
        }
        composable(
            route = ItemFoodScreen.SelectedItemFood.route,
            arguments = listOf(
                navArgument(name = SELECTED_ITEM_FOOD_ICON) { type = NavType.StringType },
                navArgument(name = SELECTED_ITEM_FOOD_NAME) { type = NavType.StringType },
                navArgument(name = SELECTED_ITEM_FOOD_PRICE) { type = NavType.StringType },
            )
        ) { backStackEntry ->
            SelectedItemScreen(
                navController,
                backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_ICON),
                backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_NAME),
                backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_PRICE),
            )
        }
        composable(
            route = ItemFoodScreen.AddToCartItemFood.route,
            arguments = listOf(
                navArgument(name = SELECTED_ITEM_FOOD_ICON) { type = NavType.StringType },
                navArgument(name = SELECTED_ITEM_FOOD_NAME) { type = NavType.StringType },
                navArgument(name = SELECTED_ITEM_FOOD_PRICE) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            AddToCardScreen(
                navController,
                backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_ICON),
                backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_NAME),
                backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_PRICE),
            )
        }
    }
}
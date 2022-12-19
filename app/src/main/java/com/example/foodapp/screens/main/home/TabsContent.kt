package com.example.foodapp.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.data.FoodModel
import com.example.foodapp.navigation.*
import com.example.foodapp.screens.main.home.homescreens.AddToCardScreen
import com.example.foodapp.screens.main.home.homescreens.SelectedItemScreen
import com.example.foodapp.screens.main.home.homescreens.ShowFoodList
import com.example.foodapp.ui.theme.GrayLite1
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
    tabs: List<FoodModel>,
    response: MutableState<List<FoodModel>?>,
    pagerState: PagerState,
    navController: NavHostController
) {


    HorizontalPager(
        count = tabs.size,
        state = pagerState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLite1)
    ) { page ->

        val list: MutableList<FoodModel> = mutableListOf()

        for(i in 0 until response.value!!.size){
            if(response.value!![i].category == tabs[page].category){
                list.add(response.value!![i])
            }
        }
        ScreenDisplayHome(list, navController)
    }
}

@Composable
fun ScreenDisplayHome(list: MutableList<FoodModel>, navControllerRoot: NavHostController) {

    val navControllerHome = rememberNavController()

    NavHost(navController = navControllerHome, startDestination = HomeSealedScreen.ShowFoodItems.route) {
        composable(HomeSealedScreen.ShowFoodItems.route) {
            ShowFoodList(list = list, navControllerHome = navControllerHome)
        }
        composable(
            route = HomeSealedScreen.SelectedItemScreen.route,
            arguments = listOf(
                navArgument(name = SELECTED_ITEM_FOOD_ID) {type = NavType.StringType},
                navArgument(name = SELECTED_ITEM_FOOD_ICON) { type = NavType.StringType },
                navArgument(name = SELECTED_ITEM_FOOD_NAME) { type = NavType.StringType },
                navArgument(name = SELECTED_ITEM_FOOD_PRICE) { type = NavType.StringType },
            )
        ) { backStackEntry ->
            SelectedItemScreen(
                navControllerHome = navControllerHome,
                navControllerRoot = navControllerRoot,
                id = backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_ID),
                icon = backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_ICON),
                name = backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_NAME),
                price = backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_PRICE),
            )
        }
        composable(
            route = HomeSealedScreen.AddFoodScreen.route,
            arguments = listOf(
                navArgument(name = SELECTED_ITEM_FOOD_ID) {type = NavType.StringType},
                navArgument(name = SELECTED_ITEM_FOOD_ICON) { type = NavType.StringType },
                navArgument(name = SELECTED_ITEM_FOOD_NAME) { type = NavType.StringType },
                navArgument(name = SELECTED_ITEM_FOOD_PRICE) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            AddToCardScreen(
                navControllerHome = navControllerHome,
                navControllerRoot = navControllerRoot,
                id = backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_ID),
                icon = backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_ICON),
                name = backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_NAME),
                price = backStackEntry.arguments?.getString(SELECTED_ITEM_FOOD_PRICE),
            )
        }
    }
}
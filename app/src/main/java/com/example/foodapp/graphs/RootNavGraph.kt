package com.example.foodapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodapp.screens.main.BottomScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.ON_BOARDING
    ) {
        onBoardingGraph(navController)
        composable(route = Graph.HOME){
            BottomScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val ON_BOARDING = "on_boarding_screen"
    const val HOME = "home_graph"
    const val ITEM = "item_graph"
}
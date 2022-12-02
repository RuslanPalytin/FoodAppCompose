package com.example.foodapp.screens.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.graphs.HomeNavigationGraph
import com.example.foodapp.navigation.BottomBarScreen
import com.example.foodapp.ui.theme.GrayLite2
import com.example.foodapp.ui.theme.Orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BottomScreen() {

    val systemUiController = rememberSystemUiController()
    systemUiController.setNavigationBarColor(GrayLite2)
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) {
        HomeNavigationGraph(navController)
    }

}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Shop,
        BottomBarScreen.Person,
        BottomBarScreen.History,
    )

    BottomNavigation(
        backgroundColor = GrayLite2
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val bottomBarDestination = items.any { it.route == currentRoute }

        if (bottomBarDestination) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                    selectedContentColor = Orange,
                    unselectedContentColor = Color.Gray,
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route){
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route){
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
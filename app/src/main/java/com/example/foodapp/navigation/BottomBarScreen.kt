package com.example.foodapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(var route: String, var icon: ImageVector, var title: String){
    object Home: BottomBarScreen(route = "home", icon = Icons.Default.Home, title = "Home")
    object Shop: BottomBarScreen(route = "shop", icon = Icons.Default.ShoppingCart, title = "Shop")
    object Person: BottomBarScreen(route = "person", icon = Icons.Default.Person, title = "Person")
    object History: BottomBarScreen(route = "history", icon = Icons.Default.Info, title = "History")
}

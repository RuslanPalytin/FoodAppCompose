package com.example.foodapp.navigation

import androidx.annotation.DrawableRes
import com.example.foodapp.R

sealed class BottomBarScreen(var route: String, @DrawableRes var icon: Int, var title: String){
    object Home: BottomBarScreen(route = "home", icon = R.drawable.ic_home, title = "Home")
    object Shop: BottomBarScreen(route = "shop", icon = R.drawable.ic_shop, title = "Shop")
    object Person: BottomBarScreen(route = "person", icon = R.drawable.ic_person, title = "Person")
    object History: BottomBarScreen(route = "history", icon = R.drawable.ic_history, title = "History")
}

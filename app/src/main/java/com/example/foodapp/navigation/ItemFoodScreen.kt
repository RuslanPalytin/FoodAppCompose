package com.example.foodapp.navigation

sealed class ItemFoodScreen(var route: String){
    object ItemFood: ItemFoodScreen(route = "item_food")
    object SelectedItemFood: ItemFoodScreen(route = "selected_item_food")
    object AddToCartItemFood: ItemFoodScreen(route = "add_to_cart")
}

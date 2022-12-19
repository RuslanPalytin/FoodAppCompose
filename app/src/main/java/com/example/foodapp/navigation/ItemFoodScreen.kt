package com.example.foodapp.navigation

const val SELECTED_ITEM_FOOD_ID = "id"
const val SELECTED_ITEM_FOOD_ICON = "icon"
const val SELECTED_ITEM_FOOD_NAME = "name"
const val SELECTED_ITEM_FOOD_PRICE = "price"

sealed class ItemFoodScreen(var route: String) {
    object OneItem : ItemFoodScreen (route = "one_item")
}

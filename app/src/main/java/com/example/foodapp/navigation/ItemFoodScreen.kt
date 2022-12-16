package com.example.foodapp.navigation

const val SELECTED_ITEM_FOOD_ID = "id"
const val SELECTED_ITEM_FOOD_ICON = "icon"
const val SELECTED_ITEM_FOOD_NAME = "name"
const val SELECTED_ITEM_FOOD_PRICE = "price"

sealed class ItemFoodScreen(var route: String) {
    object ItemFood : ItemFoodScreen(route = "item_food")
    object SelectedItemFood :
        ItemFoodScreen(route = "selected_item_food?id={id}&icon={icon}&name={name}&price={price}") {
        fun passItem(id: String, icon: String, name: String, price: String): String {
            return "selected_item_food?id=$id&icon=$icon&name=$name&price=$price"
        }
    }

    object AddToCartItemFood
        : ItemFoodScreen(route = "add_to_cart?id={id}&icon={icon}&name={name}&price={price}") {
        fun passItem(id: String, icon: String, name: String, price: String): String {
            return "add_to_cart?id=$id&icon=$icon&name=$name&price=$price"
        }
    }
    object OneItem : ItemFoodScreen (route = "one_item")
}

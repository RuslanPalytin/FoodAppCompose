package com.example.foodapp.navigation

sealed class HomeSealedScreen(val route: String) {
    object ShowFoodItems : HomeSealedScreen(route = "show_item")
    object SelectedItemScreen :
        ItemFoodScreen(route = "selected_item?id={id}&icon={icon}&name={name}&price={price}") {
        fun passItem(id: String, icon: String, name: String, price: String): String {
            return "selected_item?id=$id&icon=$icon&name=$name&price=$price"
        }

    }
    object AddFoodScreen
        : ItemFoodScreen(route = "add_item?id={id}&icon={icon}&name={name}&price={price}") {
        fun passItem(id: String, icon: String, name: String, price: String): String {
            return "add_item?id=$id&icon=$icon&name=$name&price=$price"
        }
    }
}

package com.example.foodapp.navigation

sealed class HistorySealedScreen(val route: String) {
    object HistoryListScreen : HistorySealedScreen("history_list")
    object HistoryOpenScreen :
        HistorySealedScreen("history_open?position={position}&address_and_date={address_and_date}&total_price={total_price}") {
        fun passPosition(position: Int, addressAndDate: String, totalPrice: String): String {
            return "history_open?position=$position&address_and_date=$addressAndDate&total_price=$totalPrice"
        }
    }
}

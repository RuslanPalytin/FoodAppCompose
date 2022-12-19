package com.example.foodapp.navigation

sealed class HistorySealedScreen(val route: String){
    object HistoryListScreen : HistorySealedScreen("history_list")
    object HistoryOpenScreen : HistorySealedScreen("history_open")
}

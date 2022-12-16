package com.example.foodapp.screens.main.history

sealed class HistorySealedScreen(val route: String){
    object HistoryListScreen : HistorySealedScreen("history_list")
    object HistoryOpenScreen : HistorySealedScreen("history_open")
}

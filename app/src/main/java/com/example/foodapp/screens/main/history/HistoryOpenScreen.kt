package com.example.foodapp.screens.main.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.foodapp.data.OrderModel

@Composable
fun HistoryListScreen(listHistory: List<OrderModel>, historyNavController: NavHostController) {
    LazyColumn(
        content = {
            itemsIndexed(listHistory) { index, item ->
                ItemListHistory(item, historyNavController)
            }
    })
}
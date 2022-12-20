package com.example.foodapp.screens.main.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.example.foodapp.data.OrderGetModel

@Composable
fun HistoryListScreen(
    listHistory: List<OrderGetModel>,
    historyNavController: NavHostController,
    addressAndDate: MutableState<String>
) {
    addressAndDate.value = ""
    LazyColumn(
        content = {
            itemsIndexed(listHistory) { index, item ->
                ItemListHistory(
                    item = item,
                    historyNavController = historyNavController,
                    index = index
                )
            }
    })
}
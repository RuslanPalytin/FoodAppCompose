package com.example.foodapp.screens.main.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.Italiano
import com.example.foodapp.ui.theme.NutinoRegular

@Composable
fun HistoryScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayLite1)
    ) {
        Text(
            text = "History",
            fontFamily = Italiano,
            fontSize = 48.sp,
            modifier = Modifier.padding(top = 40.dp, start = 20.dp)
        )
        HistoryPlaceHolder()
    }
}

@Preview
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(navController = rememberNavController())
}
package com.example.foodapp.screens.main.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.data.DishesModel
import com.example.foodapp.data.OrderModel

@Composable
fun ItemHistory() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color(0xFFD9D9D9),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "4th Avenue, 76", color = Color.Gray, textAlign = TextAlign.Center)
                Text(text = "02.02.2021 18:00", color = Color.Gray)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Cost", color = Color.Gray, textAlign = TextAlign.Center)
                Text(text = "20000$$", color = Color.Gray)
            }
        }
    }

}

@Preview
@Composable
fun ItemHistoryPreview() {
    ItemHistory()
}
package com.example.foodapp.screens.main.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodapp.data.OrderModel
import com.example.foodapp.navigation.HistorySealedScreen
import com.example.foodapp.ui.theme.GrayLite2
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange

@Composable
fun ItemListHistory(item: OrderModel, historyNavController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp)
            .clickable {
                historyNavController.navigate(HistorySealedScreen.HistoryOpenScreen.route)
            },
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
                Text(text = item.address, color = Color.Gray, textAlign = TextAlign.Center)
                Text(text = item.date, color = Color.Gray)
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

@Composable
fun ItemOpenHistory(item: OrderModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .background(color = GrayLite2)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
            ) {
                AsyncImage(model = item, contentDescription = null)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "item.name",
                    fontFamily = NutinoRegular,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = "item.price",
                    textAlign = TextAlign.Center,
                    color = Orange,
                    fontFamily = NutinoRegular,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
            }
        }
    }
}
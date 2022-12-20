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
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodapp.data.DishesGetModel
import com.example.foodapp.data.OrderGetModel
import com.example.foodapp.navigation.HistorySealedScreen
import com.example.foodapp.ui.theme.*

@Composable
fun ItemListHistory(item: OrderGetModel, historyNavController: NavHostController, index: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp)
            .clickable {
                historyNavController.navigate(
                    HistorySealedScreen.HistoryOpenScreen.passPosition(
                        position = index,
                        addressAndDate = item.address + " " + item.date,
                        totalPrice = item.totalPrice
                    )
                )
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
                Text(
                    text = item.address,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontFamily = Roboto,
                    fontSize = 18.sp
                )
                Text(
                    text = item.date,
                    color = Color.Gray,
                    fontFamily = Roboto,
                    fontSize = 18.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Cost",
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    fontFamily = NutinoRegular,
                    fontSize = 17.sp
                )
                Text(
                    text = item.totalPrice,
                    color = Color.Gray,
                    fontFamily = NutinoRegular,
                    fontSize = 17.sp
                )
            }
        }
    }
}

@Composable
fun ItemOpenHistory(item: DishesGetModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 10.dp)
            .background(color = GrayLite1)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
            ) {
                AsyncImage(model = item.dish.icon, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.dish.name,
                    fontFamily = NutinoRegular,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(7.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        text = item.dish.price,
                        textAlign = TextAlign.Center,
                        color = Orange,
                        fontFamily = NutinoRegular,
                        fontSize = 15.sp
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.count,
                            textAlign = TextAlign.Center,
                            color = Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
            }
        }
    }
}
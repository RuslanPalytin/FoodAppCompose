package com.example.foodapp.screens.main.shop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodapp.data.FoodModelDb
import com.example.foodapp.ui.theme.GrayLite2
import com.example.foodapp.R
import com.example.foodapp.storage.DbHandler
import com.example.foodapp.ui.theme.GrayLite3
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemFoodOrder(item: FoodModelDb) {

    val context = LocalContext.current
    var count by remember { mutableStateOf(item.count)}
    var decItem by remember { mutableStateOf(true)}
    val db = DbHandler(context)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .background(color = GrayLite2)
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    db.deleteFood(item.id)
                }
            )
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
                AsyncImage(model = item.icon, contentDescription = null)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item.name,
                    fontFamily = NutinoRegular,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(7.dp))
                Text(
                    text = item.price,
                    textAlign = TextAlign.Center,
                    color = Orange,
                    fontFamily = NutinoRegular,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
                Row() {

                    decItem = count > 1

                    Card(
                        modifier = Modifier.size(28.dp),
                        backgroundColor = GrayLite3,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable(enabled = decItem) {
                                    count--
                                },
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = null,
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = count.toString(), color = Color.Gray, fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Card(
                        modifier = Modifier.size(28.dp),
                        backgroundColor = GrayLite3,
                        shape = RoundedCornerShape(6.dp),
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {
                                    count++
                                    db.updateFood(newCount = count, foodModel = item)
                                },
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}
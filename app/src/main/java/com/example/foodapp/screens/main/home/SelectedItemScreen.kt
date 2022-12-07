package com.example.foodapp.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodapp.R
import com.example.foodapp.ui.theme.GrayLite3
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange

@Composable
fun SelectedItemScreen() {

    val count = remember { mutableStateOf(1) }
    val decItem = remember { mutableStateOf(true) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(30.dp),
        backgroundColor = Color.White,
        elevation = 5.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .padding(horizontal = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_selected_cancel),
                    contentDescription = null
                )
                Text(text = "More", fontSize = 18.sp, fontFamily = NutinoRegular)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Card(
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(130.dp),
                    backgroundColor = Color.White,
                    elevation = 70.dp
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_tomato),
                        contentDescription = null,
                    )
                }
                Column(
                    modifier = Modifier.height(150.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Veggie tomato mix",
                        fontFamily = NutinoRegular,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "1900",
                        color = Orange,
                        fontFamily = NutinoRegular,
                        fontSize = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(28.dp),
                        backgroundColor = GrayLite3,
                        shape = RoundedCornerShape(6.dp)
                    ) {

                        decItem.value = count.value > 1

                        Icon(
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable(enabled = decItem.value, onClick = {
                                    count.value--
                                }),
                            painter = painterResource(id = R.drawable.ic_minus),
                            contentDescription = null,
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = count.value.toString(), color = Color.Gray, fontSize = 16.sp)
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
                                    count.value++
                                },
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = null,
                        )
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(end = 15.dp),
                        backgroundColor = Orange,
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Add to cart",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontFamily = NutinoRegular
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_shopping_cart),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
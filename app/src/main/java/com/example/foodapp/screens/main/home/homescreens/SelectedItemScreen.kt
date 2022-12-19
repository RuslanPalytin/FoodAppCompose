package com.example.foodapp.screens.main.home.homescreens

import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodapp.R
import com.example.foodapp.data.FoodModelDb
import com.example.foodapp.graphs.Graph
import com.example.foodapp.navigation.HomeSealedScreen
import com.example.foodapp.navigation.ItemFoodScreen
import com.example.foodapp.storage.DbHandler
import com.example.foodapp.ui.theme.GrayLite3
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange

@Composable
fun SelectedItemScreen(
    navControllerHome: NavHostController,
    navControllerRoot: NavHostController,
    id: String?,
    icon: String?,
    name: String?,
    price: String?,
) {
    val count = remember { mutableStateOf(1) }
    val decItem = remember { mutableStateOf(true) }
    val context = LocalContext.current
    val localDataBase = DbHandler(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .padding(horizontal = 10.dp)
            .padding(top = 15.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp),
            backgroundColor = Color.White,
            elevation = 5.dp
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_selected_cancel),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            navControllerRoot.navigate(Graph.HOME)
                        }
                    )
                    Text(
                        text = "More",
                        fontSize = 18.sp,
                        fontFamily = NutinoRegular,
                        modifier = Modifier.clickable {
                            navControllerRoot.navigate(ItemFoodScreen.OneItem.route)
                        })
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
                        AsyncImage(model = icon, contentDescription = null)
                    }
                    Column(
                        modifier = Modifier.height(150.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = name.toString(),
                            fontFamily = NutinoRegular,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = price.toString(),
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
                            backgroundColor = Orange,
                            shape = RoundedCornerShape(30.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(end = 15.dp)
                                .clickable {

                                    navControllerHome.navigate(
                                        HomeSealedScreen.AddFoodScreen.passItem(
                                            id = id.toString(),
                                            icon = icon.toString(),
                                            name = name.toString(),
                                            price = price.toString()
                                        )
                                    )

                                    localDataBase.addNewFood(
                                        FoodModelDb(
                                            id = id.toString(),
                                            icon = icon.toString(),
                                            name = name.toString(),
                                            price = price.toString(),
                                            count = count.value
                                        )
                                    )
                                }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                                    .clickable {
                                        navControllerHome.navigate(
                                            HomeSealedScreen.AddFoodScreen.passItem(
                                                id = id.toString(),
                                                icon = icon.toString(),
                                                name = name.toString(),
                                                price = price.toString()
                                            )
                                        )

                                        localDataBase.addNewFood(
                                            FoodModelDb(
                                                id = id.toString(),
                                                icon = icon.toString(),
                                                name = name.toString(),
                                                price = price.toString(),
                                                count = count.value
                                            )
                                        )
                                    },
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
}
package com.example.foodapp.screens.main.home.homescreens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.foodapp.R
import com.example.foodapp.graphs.Graph
import com.example.foodapp.navigation.BottomBarScreen
import com.example.foodapp.navigation.HomeSealedScreen
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange

@Composable
fun AddToCardScreen(
    navControllerHome: NavHostController,
    navControllerRoot: NavHostController,
    id: String?,
    icon: String?,
    name: String?,
    price: String?
) {
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
                        .padding(top = 10.dp)
                        .padding(horizontal = 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_selected_cancel),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            navControllerHome.navigate(
                                HomeSealedScreen.SelectedItemScreen.passItem(
                                    id = id.toString(),
                                    icon = icon.toString(),
                                    name = name.toString(),
                                    price = price.toString()
                                )
                            )
                        }
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                ) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        onClick = { navControllerRoot.navigate(Graph.HOME) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text(
                            text = "Continue Shop",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = NutinoRegular
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        onClick = {
                            navControllerRoot.navigate(Graph.HOME)
                            navControllerRoot.navigate(BottomBarScreen.Shop.route) {
                                navControllerRoot.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                        shape = RoundedCornerShape(30.dp)
                    ) {
                        Text(
                            text = "Go to cart",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontFamily = NutinoRegular
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
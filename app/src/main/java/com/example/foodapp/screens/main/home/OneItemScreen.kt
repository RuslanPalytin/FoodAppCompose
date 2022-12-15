package com.example.foodapp.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.foodapp.R
import com.example.foodapp.data.FoodModel
import com.example.foodapp.graphs.Graph
import com.example.foodapp.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OneItemScreen(navController: NavHostController) {

    val pagerState = rememberPagerState()
    val context = LocalContext.current
    val response = remember { mutableStateOf<List<FoodModel>?>(null) }
    getListFoods(context = context, result = response)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_selected_cancel),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 25.dp, start = 20.dp)
                .clickable {
                    navController.navigate(Graph.HOME)
                }
        )
        Spacer(modifier = Modifier.height(20.dp))
        val count = remember { mutableStateOf(1) }
        if (response.value != null) {
            HorizontalPager(
                state = pagerState,
                count = response.value?.size!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            ) { page ->
                ItemIcon(response.value?.get(page)?.icon)
                count.value = page
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    activeColor = Orange,
                    inactiveColor = GrayLite3,
                    indicatorHeight = 10.dp,
                    indicatorWidth = 10.dp
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            ItemInfo(
                response.value?.get(count.value)?.name,
                response.value?.get(count.value)?.price
            )
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(text = "Delivery info", fontFamily = NutinoRegular, fontSize = 17.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Delivered between monday aug and thursday 20 from 8pm to 91:32 pm",
                    fontFamily = NutinoRegular,
                    fontSize = 15.sp,
                    color = GrayLite3
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Orange),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                ) {
                    Text(
                        text = "Add to cart",
                        color = Color.White,
                        fontFamily = NutinoRegular,
                        fontSize = 17.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ItemIcon(icon: String?) {
    Card(
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(250.dp),
        backgroundColor = Background,
        elevation = 100.dp
    ) {
        AsyncImage(
            model = icon,
            contentDescription = null,
            modifier = Modifier.background(color = Background)
        )
    }
}

@Composable
fun ItemInfo(name: String?, price: String?) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) {
        Text(text = name.toString(), fontFamily = NutinoRegular, fontSize = 28.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = price.toString(), fontFamily = NutinoRegular, fontSize = 22.sp, color = Orange)
    }
}

@Preview
@Composable
fun OneItemScreenPreview() {
    OneItemScreen(rememberNavController())
}
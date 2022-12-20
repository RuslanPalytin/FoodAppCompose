package com.example.foodapp.screens.main.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.data.OrderGetModel
import com.example.foodapp.navigation.HistorySealedScreen
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.Italiano
import com.example.foodapp.ui.theme.Roboto

const val HISTORY_ITEM_POSITION = "position"
const val HISTORY_ADDRESS_AND_DATE = "address_and_date"
const val HISTORY_TOTAL_PRICE = "total_price"

@Composable
fun HistoryScreen(navController: NavHostController) {

    val historyNavController = rememberNavController()
    val context = LocalContext.current
    val response = remember { mutableStateOf<List<OrderGetModel>?>(null) }
    val addressAndDate = remember { mutableStateOf("") }
    getHistoryFoodsApi(context = context, result = response)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayLite1),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "History",
                fontFamily = Italiano,
                fontSize = 48.sp,
            )
            Text(
                text = addressAndDate.value,
                textAlign = TextAlign.Center,
                fontFamily = Roboto,
                fontSize = 18.sp,
                color = Color.Gray
            )
        }

        if (response.value == null) {
            HistoryPlaceHolder()
        } else {
            Spacer(modifier = Modifier.height(10.dp))
            NavHost(
                navController = historyNavController,
                startDestination = HistorySealedScreen.HistoryListScreen.route
            ) {
                composable(route = HistorySealedScreen.HistoryListScreen.route) {
                    if (response.value != null) {
                        HistoryListScreen(
                            listHistory = response.value!!,
                            historyNavController = historyNavController,
                            addressAndDate = addressAndDate
                        )
                    }
                }
                composable(
                    route = HistorySealedScreen.HistoryOpenScreen.route,
                    arguments = listOf(
                        navArgument(name = HISTORY_ITEM_POSITION) { type = NavType.IntType },
                        navArgument(name = HISTORY_ADDRESS_AND_DATE) { type = NavType.StringType },
                        navArgument(name = HISTORY_TOTAL_PRICE) { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    HistoryOpenScreen(
                        navController = navController,
                        items = response.value!!,
                        position = backStackEntry.arguments?.getInt(HISTORY_ITEM_POSITION)!!,
                        addressAndDate = backStackEntry.arguments?.getString(
                            HISTORY_ADDRESS_AND_DATE
                        )!!,
                        totalPrice = backStackEntry.arguments?.getString(HISTORY_TOTAL_PRICE)!!,
                        mutableAddressAndDate = addressAndDate
                    )
                }
            }
        }
    }
}
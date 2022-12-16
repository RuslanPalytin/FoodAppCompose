package com.example.foodapp.screens.main.history

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.api.ApiService
import com.example.foodapp.data.OrderModel
import com.example.foodapp.storage.SharedPreference
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.Italiano
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HistoryScreen(navController: NavHostController) {

    val historyNavController = rememberNavController()
    val context = LocalContext.current
    val response = remember { mutableStateOf<List<OrderModel>?>(null) }
    getHistoryFoodsApi(context = context, result = response)

    NavHost(navController = historyNavController, startDestination = HistorySealedScreen.HistoryListScreen.route) {
        composable(route = HistorySealedScreen.HistoryOpenScreen.route){
            HistoryOpenScreen()
        }
        composable(route = HistorySealedScreen.HistoryListScreen.route) {
            if(response.value != null){
                HistoryListScreen(response.value!!, historyNavController)
            }
        }
    }
}

@Preview
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(navController = rememberNavController())
}

@Composable
fun EmptyHistory() {
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
    }
}

fun getHistoryFoodsApi(context: Context, result: MutableState<List<OrderModel>?>) {

    val token = SharedPreference(context).readToken()

    ApiService.retrofit.getOrdersHistory(token = "Bearer $token")!!.enqueue(object: Callback<List<OrderModel>?> {
        override fun onResponse(
            call: Call<List<OrderModel>?>,
            response: Response<List<OrderModel>?>
        ) {
            if(response.isSuccessful) {
                result.value = response.body()
            }
        }

        override fun onFailure(call: Call<List<OrderModel>?>, t: Throwable) {
            TODO("Not yet implemented")
        }
    })
}
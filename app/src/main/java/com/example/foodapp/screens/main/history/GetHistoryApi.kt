package com.example.foodapp.screens.main.history

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.example.foodapp.api.ApiService
import com.example.foodapp.data.OrderGetModel
import com.example.foodapp.storage.SharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getHistoryFoodsApi(context: Context, result: MutableState<List<OrderGetModel>?>) {

    val token = SharedPreference(context).readToken()

    ApiService.retrofit.getOrdersHistory(token = "Bearer $token")!!
        .enqueue(object : Callback<List<OrderGetModel>?> {
            override fun onResponse(
                call: Call<List<OrderGetModel>?>,
                response: Response<List<OrderGetModel>?>
            ) {
                if (response.isSuccessful) {
                    result.value = response.body()
                } else {
                    Toast.makeText(context, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<OrderGetModel>?>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
}
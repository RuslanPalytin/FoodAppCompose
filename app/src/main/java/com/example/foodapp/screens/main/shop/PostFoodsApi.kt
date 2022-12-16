package com.example.foodapp.screens.main.shop

import android.content.Context
import android.widget.Toast
import com.example.foodapp.api.ApiService
import com.example.foodapp.data.OrderModel
import com.example.foodapp.storage.SharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun postFoods(items: OrderModel, context: Context) {

    val token = SharedPreference(context).readToken()


    ApiService.retrofit.createOrders(token = "Bearer $token", orderModel = items).enqueue(object : Callback<Unit> {
        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
            if(response.isSuccessful){
                Toast.makeText(context, "Add orders!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<Unit>, t: Throwable) {
            Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
        }
    })
}
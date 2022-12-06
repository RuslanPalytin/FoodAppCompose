package com.example.foodapp.screens.main.home

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import com.example.foodapp.api.ApiService
import com.example.foodapp.data.FoodModel
import com.example.foodapp.storage.SharedPreference
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun getListFoods(context: Context, result: MutableState<List<FoodModel>?>) {

    val token = SharedPreference(context)

    ApiService.retrofit.getFoods("Bearer ${token.readToken()}", "1.0")!!
        .enqueue(object : Callback<List<FoodModel>?> {
            override fun onResponse(
                call: Call<List<FoodModel>?>,
                response: Response<List<FoodModel>?>
            ) {
                if (response.isSuccessful) {
                    val resp = response.body()!!
                    result.value = resp
                } else {
                    Toast.makeText(context, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<FoodModel>?>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
}
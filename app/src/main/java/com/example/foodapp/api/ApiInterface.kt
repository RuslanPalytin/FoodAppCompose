package com.example.foodapp.api

import com.example.foodapp.data.FoodModel
import com.example.foodapp.data.LoginUserModel
import com.example.foodapp.data.RegisterUserModel
import com.example.foodapp.data.Token
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.*

interface ApiInterface {

    @POST("api/auth/registration")
    fun createUser(
        @Body registerUser: RegisterUserModel
    ): Call<Token>

    @POST("api/auth/login")
    fun loginUser(
        @Body loginUser: LoginUserModel
    ): Call<Token>

    @GET ("api/delivery/dishes")
    fun getFoods(
        @Header("Authorization") token: String,
        @Query("version") version: String,
    ): Call<List<FoodModel>>
}
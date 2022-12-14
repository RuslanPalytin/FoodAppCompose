package com.example.foodapp.data

data class OrderModel(
    val address: String,
    val date: String,
    val dishes: List<DishesModel>
)

data class DishesModel(
    val id: String,
    val count: String
)

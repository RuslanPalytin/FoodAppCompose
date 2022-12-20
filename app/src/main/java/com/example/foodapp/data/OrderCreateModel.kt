package com.example.foodapp.data

data class OrderCreateModel(
    val address: String,
    val date: String,
    val dishes: MutableList<DishesCreateModel>,
    val totalPrice: String,
)

data class DishesCreateModel(
    val id: String,
    val count: String
)

package com.example.foodapp.data

data class OrderGetModel(
    val userId: String,
    val courierId: String,
    val address: String,
    val date: String,
    val totalPrice: String,
    val dishes: MutableList<DishesGetModel>,
    val completed: Boolean
)

data class DishesGetModel(
    val dish: DishGetModel,
    val count: String
)

data class DishGetModel(
    val id: String,
    val category: String,
    val name: String,
    val price: String,
    val icon: String,
    val version: String
)

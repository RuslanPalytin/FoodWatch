package com.example.foodwatch.model

data class OrderModel(
    val userId: String,
    val courierId: String,
    val address: String,
    val date: String,
    val totalPrice: String,
    val dishes: MutableList<Dishes>
)

data class Dishes(
    val dish: Dish,
    val count: String
)

data class Dish(
    val category: String,
    val name: String,
    val price: String,
    val icon: String,
    val version: String,
    val id: String
)

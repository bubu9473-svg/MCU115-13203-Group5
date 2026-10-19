package com.example.hw3


data class Order(
    var mainMeal: String = "",
    var sideDishes: List<String> = emptyList(), // 副餐可以複選
    var drink: String = ""
) {

    fun isComplete(): Boolean {
        return mainMeal.isNotEmpty() && sideDishes.isNotEmpty() && drink.isNotEmpty()
    }
}
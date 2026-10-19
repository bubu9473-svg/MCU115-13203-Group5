package com.example.hw3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class OrderViewModel : ViewModel() {
    private val _order = MutableLiveData<Order>(Order())
    val order: LiveData<Order> = _order

    fun setMainMeal(meal: String) {
        val current = _order.value ?: Order()
        current.mainMeal = meal
        _order.value = current
    }
    fun setSideDishes(sides: List<String>) {
        val current = _order.value ?: Order()
        current.sideDishes = sides
        _order.value = current
    }


    fun setDrink(drk: String) {
        val current = _order.value ?: Order()
        current.drink = drk
        _order.value = current
    }


    fun resetOrder() {
        _order.value = Order()
    }
}
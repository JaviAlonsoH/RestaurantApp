package com.example.restaurantapp.network.response

import com.example.restaurantapp.model.RestaurantObject
import com.google.gson.annotations.Expose

data class RestResponse (
    @Expose
    val data: MutableList<Restaurant>
)

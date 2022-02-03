package com.example.restaurantapp.model


import com.google.gson.annotations.SerializedName

data class RestaurantObject(
    @SerializedName("idRest")
    val idRest: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("foodType")
    val foodType: String,
    @SerializedName("comments")
    val comments: String,
    @SerializedName("rating")
    val rating: Double,

)
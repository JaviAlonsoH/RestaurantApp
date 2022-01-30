package com.example.restaurantapp.network.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RestRequest(
    @SerializedName("idRest")
    @Expose
    val idRest: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("foodType")
    @Expose
    val foodType: String
)
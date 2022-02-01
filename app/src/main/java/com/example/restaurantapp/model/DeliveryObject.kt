package com.example.restaurantapp.model

import com.google.gson.annotations.SerializedName

data class DeliveryObject (
    @SerializedName("idDelivery")
    val idDelivery: Int,
    @SerializedName("vehicle")
    val vehicle: String,
    @SerializedName("restaurant")
    val restaurant: String,
    @SerializedName("address")
    val address: String

    )
package com.example.restaurantapp.network.response

import com.example.restaurantapp.db.entity.RestEntity
import com.example.restaurantapp.model.RestaurantObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Restaurant (
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

fun Restaurant.toRestObjectExt(): RestaurantObject {
    return RestaurantObject(idRest, name, foodType)
}

fun List<Restaurant>?.toMap(): List<RestaurantObject> {
    return this?.map { it.toRestObjectExt() } ?: emptyList()
}


fun Restaurant.toEntity(): RestEntity {
    return RestEntity(idRest, name, foodType)
}

fun List<Restaurant>?.toEntity(): List<RestEntity> {
    return this?.map { it.toEntity() } ?: emptyList()
}

fun RestEntity.toModel(): RestaurantObject {
    return RestaurantObject(idRest, name, foodType)
}

fun List<RestEntity>?.toModel(): List<RestaurantObject> {
    return this?.map { it.toModel() } ?: emptyList()
}
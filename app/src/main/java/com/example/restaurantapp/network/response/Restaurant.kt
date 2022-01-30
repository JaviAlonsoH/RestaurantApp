package com.example.restaurantapp.network.response

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

/*
fun Restaurant.toEntity(): MsgEntity {
    return MsgEntity(idMsg, msg, date)
}

fun List<Msg>?.toEntity(): List<MsgEntity> {
    return this?.map { it.toEntity() } ?: emptyList()
}

fun MsgEntity.toModel(): MsgObject {
    return MsgObject(id, content, date, 2)
}

fun List<MsgEntity>?.toModel(): List<MsgObject> {
    return this?.map { it.toModel() } ?: emptyList()
}
*
 */
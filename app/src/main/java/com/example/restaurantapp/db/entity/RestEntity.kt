package com.example.restaurantapp.db.entity

import androidx.room.*

@Entity(tableName = "restaurant")
data class RestEntity (
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "foodType")
    val foodType: String,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @PrimaryKey(autoGenerate = true)
    val idRest: Int? = null
)

data class RestaurantDeliveries (
    @Embedded
    val restEntity: RestEntity,
    @Relation(
        parentColumn = "idRest",
        entityColumn = "idDelivery"
    )
    val deliveryList: List<DeliveryEntity>
)
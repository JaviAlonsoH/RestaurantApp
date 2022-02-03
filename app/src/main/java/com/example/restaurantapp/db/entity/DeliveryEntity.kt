package com.example.restaurantapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "delivery")
data class DeliveryEntity (
    @PrimaryKey(autoGenerate = true)
    val idDelivery: Int?,
    @ColumnInfo(name = "vehicle")
    val vehicle: String,
    @ColumnInfo(name = "restId")
    val idRest: Int,
    @ColumnInfo(name = "address")
    val address: String,
)
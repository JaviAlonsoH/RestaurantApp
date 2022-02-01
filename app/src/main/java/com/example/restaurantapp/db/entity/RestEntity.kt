package com.example.restaurantapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant")
data class RestEntity (
    @PrimaryKey(autoGenerate = true)
    val idRest: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "foodType")
    val foodType: String,
    @ColumnInfo(name = "comments")
    val comments: String,
    @ColumnInfo(name = "rating")
    val rating: Double
)
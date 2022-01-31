package com.example.restaurantapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.restaurantapp.db.entity.RestEntity

@Database(entities = [RestEntity::class], version = 1)
abstract class RestDB : RoomDatabase() {
    abstract fun restDao(): RestDAO
}
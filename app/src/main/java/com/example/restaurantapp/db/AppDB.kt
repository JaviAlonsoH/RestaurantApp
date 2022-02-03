package com.example.restaurantapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.restaurantapp.db.entity.DeliveryEntity
import com.example.restaurantapp.db.entity.RestEntity

@Database(entities = [RestEntity::class, DeliveryEntity::class], version = 1)
abstract class RestDB : RoomDatabase() {
    abstract fun restDao(): RestDAO

    companion object {
        @Volatile
        private var INSTANCE: RestDB? = null

        fun getInstance(context: Context): RestDB = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, RestDB::class.java, "task.db")
                .allowMainThreadQueries()
                .build()
    }
}
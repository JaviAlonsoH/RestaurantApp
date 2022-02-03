package com.example.restaurantapp.db

import androidx.room.*
import com.example.restaurantapp.db.entity.DeliveryEntity
import com.example.restaurantapp.db.entity.RestEntity
import com.example.restaurantapp.db.entity.RestaurantDeliveries

@Dao
interface RestDAO {

    @Query("SELECT * FROM restaurant")
    fun findAll(): List<RestEntity>

    @Query("SELECT * FROM restaurant WHERE restaurant.idRest = :idRest LIMIT 1")
    fun findRestById(idRest: Int): RestEntity

    @Query("SELECT * FROM restaurant WHERE restaurant.name LIKE :query")
    fun findRestById(query: String): RestEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRest(restsEntities: List<RestEntity>)

    @Delete
    fun deleteRest(restEntity: RestEntity)

    //Deliveries

    @Query("SELECT * FROM restaurant JOIN delivery ON restaurant.idRest = delivery.idDelivery")
    fun getRestDeliveries(): Map<RestEntity, List<DeliveryEntity>>

    @Query("SELECT * FROM restaurant")
    fun getRestDeliveriesList(): List<RestaurantDeliveries>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDlv(deliveryEntity: List<DeliveryEntity>)

    @Delete
    fun delDlv(deliveryEntity: DeliveryEntity)



}
package com.example.restaurantapp.network

import com.example.restaurantapp.network.request.RestRequest
import com.example.restaurantapp.network.response.RestResponse
import com.example.restaurantapp.network.response.Restaurant
import retrofit2.Call
import retrofit2.http.*

interface Service {
    @GET("restaurant")
    fun getRests(): Call<RestResponse>

    @POST("addrestaurant")
    fun addRest(@Body req: RestRequest): Call<Void>

    @DELETE("deleterestaurant/{restId}")
    fun delRest(@Path("restId") restId: Int): Call<Void>

    //Deliveries

    @GET("deliveries")
    fun getDlvs(): Call<RestResponse>

    @POST("adddelivery")
    fun addDlv(@Body req: RestRequest): Call<Void>

    @DELETE("deletedelivery/{idDelivery}")
    fun delDlv(@Path("idDelivery") idDelivery: Int): Call<Void>

}
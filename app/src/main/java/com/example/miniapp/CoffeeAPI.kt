package com.example.miniapp

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoffeeAPI {

    @GET("api/coffee/random_coffee")
//    fun getRandomCoffee(): Call<CoffeeData>
    fun getRandomCoffee(): Call<JsonObject>

    @GET("api/coffee/random_coffee")
    fun getRandomCoffees(@Query("size") size: Int): Call<List<CoffeeData>>
}
package com.example.miniapp

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface CoffeeAPI {

    @GET("api/coffee/random_coffee")
//    fun getRandomCoffee(): Call<CoffeeData>
    fun getRandomCoffee(): Call<JsonObject>
}
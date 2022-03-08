package com.example.miniapp

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ComputerAPI {
    @GET("api/computer/random_computer")
    fun getRandomComputer(): Call<JsonObject>
}
package com.example.miniapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CoffeeViewModel: ViewModel() {
    var coffee = mutableStateOf("")

    val api by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://random-data-api.com/")
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create<CoffeeAPI>()
    }

    fun getRandomCoffeeData() {
        viewModelScope.launch {
            val res = api.getRandomCoffee().awaitResponse()

            if (res.isSuccessful) {
                val data: JsonObject? = res.body()
//                Log.d("***********", data!!.get("blend_name").toString())
//                coffee.value = data!!.get("blend_name").toString()
                coffee.value = data!!.get("blend_name").asString
            } else {
                Log.d("***********", "not success")
            }
        }
    }
}
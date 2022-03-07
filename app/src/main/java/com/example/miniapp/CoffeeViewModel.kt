package com.example.miniapp

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class CoffeeViewModel: ViewModel() {
    var coffeeName = mutableStateOf("")
    var coffeeNote = mutableStateOf("")
    var coffees = mutableStateOf(listOf<String>())

    val api by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://random-data-api.com/")
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create<CoffeeAPI>()
    }

    fun getRandomCoffeeNameData() {
        viewModelScope.launch {
            val res = api.getRandomCoffee().awaitResponse()

            if (res.isSuccessful) {
                val data: JsonObject? = res.body()
//                Log.d("***********", data!!.get("blend_name").toString())
//                coffeeName.value = data!!.get("blend_name").toString()
                coffeeName.value = data!!.get("blend_name").asString
            } else {
                Log.d("***********", "not success")
            }
        }
    }

    fun getRandomCoffees() {
        viewModelScope.launch {
            val res = api.getRandomCoffees(10).awaitResponse()

            if (res.isSuccessful) {
                val data: List<CoffeeData>? = res.body()

                val coffeeList = mutableListOf<String>()
                data!!.forEach {
                    coffeeList.add( it.blend_name )
                }

                coffees.value = coffeeList

            } else {
                Log.d("***********", "not success")
            }
        }
    }

    fun getRandomCoffeeData() {
        viewModelScope.launch {
            val res = api.getRandomCoffee().awaitResponse()

            if (res.isSuccessful) {
                val data: JsonObject? = res.body()
                coffeeName.value = data!!.get("blend_name").asString
                coffeeNote.value = data!!.get("notes").asString
            } else {
                Log.d("***********", "not success")
            }
        }
    }
}
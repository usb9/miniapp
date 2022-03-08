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

class ComputerViewModel: ViewModel() {
    var platform = mutableStateOf("")
    var os = mutableStateOf("")

    val api by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://random-data-api.com/")
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create<ComputerAPI>()
    }

    fun getRandomComputerData() {
        viewModelScope.launch {
            val res = api.getRandomComputer().awaitResponse()

            if (res.isSuccessful) {
                val data: JsonObject? = res.body()
                platform.value = data!!.get("platform").asString
                os.value = data!!.get("os").asString
            } else {
                Log.d("***********", "not success")
            }
        }
    }
}
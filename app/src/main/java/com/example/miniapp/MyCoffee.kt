package com.example.miniapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MyCoffee() {
    val coffeeVM = viewModel<CoffeeViewModel>()

    Column() {
        Button(onClick = { coffeeVM.getRandomCoffeeData() }) {
            Text("Click here to see coffee data")
        }

        Text(text = coffeeVM.coffee.value)
    }
}
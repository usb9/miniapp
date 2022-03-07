package com.example.miniapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CoffeeView() {
    val coffeeVM = viewModel<CoffeeViewModel>()

    Column() {
//        Button(onClick = { coffeeVM.getRandomCoffeeNameData() }) {
//            Text("Click here to see coffee name")
//        }
//        Text(text = coffeeVM.coffeeName.value)
//
//        Button(onClick = { coffeeVM.getRandomCoffees() }) {
//            Text("Click here to see coffees")
//        }
//        coffeeVM.coffees.value.forEach {
//            Text(text = it)
//        }

        Button(onClick = { coffeeVM.getRandomCoffeeData() }) {
            Text("Click here to see coffee detail")
        }
        Text(text = coffeeVM.coffeeName.value)
        Text(text = coffeeVM.coffeeNote.value)
    }
}
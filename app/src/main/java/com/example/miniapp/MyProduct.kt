package com.example.miniapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun MyProduct() {
    val computerVM = viewModel<ComputerViewModel>()

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 5.dp
        ) {
            AsyncImage(
                model = "https://i.imgur.com/8hSyPhv.png",
                contentDescription = ""
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
                .height(120.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Button(onClick = { computerVM.getRandomComputerData() }) {
                Text("Click here to see computer OS detail")
            }
            Text(text = computerVM.platform.value)
            Text(text = computerVM.os.value)
        }
    }
}
package com.example.miniapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun MyUser() {
    var isRegistered by remember { mutableStateOf(false)}

    Column(Modifier.padding(horizontal = 10.dp)) {
        Row(Modifier.padding(top = 50.dp, bottom = 10.dp)) {
            Checkbox(checked = isRegistered, onCheckedChange = {isRegistered = !isRegistered})
            Text(text = "Already registered")
        }

        Row(Modifier.padding(top = 10.dp, bottom = 10.dp)) {
            Text(
                text = if(isRegistered) "Log in" else "Register",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if( !isRegistered ){
            MyInputField(label = "Username")
            MyInputField(label = "Password")
            MyButtonState(nameButton = "Signup")
        } else {
            MyInputField(label = "Username")
            MyInputField(label = "Password")
            MyButtonState(nameButton = "Login")
        }
    }
}

@Composable
fun MyInputField(label: String) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .padding(10.dp)
            .width(240.dp),
        label = { Text(text = label) }
    )
}

@Composable
fun MyInputFieldState(label: String) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .padding(10.dp)
            .width(240.dp),
        label = { Text(text = label) }
    )
}

@Composable
fun MyButtonState(nameButton: String) {
    Row(Modifier.padding(top = 10.dp, bottom = 10.dp)) {
        Button(
            onClick = {},
            modifier = Modifier
                .width(100.dp)
                .background(Color.LightGray)
        ) {
            Text(text = nameButton)
        }
    }
}
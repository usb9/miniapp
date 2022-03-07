package com.example.miniapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun MyUser(userVM: UserViewModel) {
    var isRegistered by remember { mutableStateOf(false)}

    Column(Modifier.padding(horizontal = 10.dp)) {
        Row(Modifier.padding(top = 50.dp, bottom = 10.dp)) {
            Checkbox(checked = isRegistered, onCheckedChange = {isRegistered = !isRegistered})
            Text(text = "Already registered")
        }

        Row(Modifier.padding(top = 10.dp, bottom = 10.dp)) {
            Text(
                text = if(isRegistered) "Log in" else "Register",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        if( !isRegistered ){
//            MyInputField(label = "Username")
//            MyInputField(label = "Password")
//            MyButtonState(nameButton = "Signup")
            SignupView()
        } else {
//            MyInputField(label = "Username")
//            MyInputField(label = "Password")
//            MyButtonState(nameButton = "Login")
            LoginView(userVM)
        }
    }
}

@Composable
fun SignupView() {
    var email = remember { mutableStateOf("") }
    var pw = remember { mutableStateOf("") }
    var statusSignup by remember { mutableStateOf(false) }

    val fAuth = Firebase.auth

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyOutlineTextField(text = email, label = "Email", isPw = false)
        MyOutlineTextField(text = pw, label = "Password", isPw = true)

        OutlinedButton( onClick = {
            fAuth
                .createUserWithEmailAndPassword(email.value, pw.value)
                .addOnSuccessListener {
                    email.value = ""
                    pw.value = ""
                    statusSignup = true
                }
                .addOnFailureListener() {
                    Log.d("************", it.message.toString())
                }
        }) {
            Text(text = "SignUp")
        }
        if (statusSignup) {
            Text(text = "SignUp is success, pls login")
        }
    }
}

@Composable
fun LoginView(userVM: UserViewModel) {
    var email = remember { mutableStateOf("") }
    var pw = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MyOutlineTextField(text = email, label = "Email", isPw = false)
        MyOutlineTextField(text = pw, label = "Password", isPw = true)

        OutlinedButton( onClick = { userVM.loginUser(email.value, pw.value)}) {
            Text(text = "Login")
        }
    }
}

@Composable
fun MyOutlineTextField(text: MutableState<String>, label: String, isPw: Boolean) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        label = { Text(label) },
        visualTransformation =
        if(isPw)
            PasswordVisualTransformation()
        else
            VisualTransformation.None
    )
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
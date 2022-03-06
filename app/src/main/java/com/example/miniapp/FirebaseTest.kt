package com.example.miniapp

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//@Composable
//fun MainView() {
//    val userVM = viewModel<UserViewModel>()
//
//    if(userVM.username.value.isEmpty()){
//        LoginView(userVM)
//    }else {
//        Text(text = userVM.username.value)
//    }
//}

//@Composable
//fun LoginView(userVM: UserViewModel) {
//    var email = remember { mutableStateOf("") }
//    var pw = remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(200.dp),
//        verticalArrangement = Arrangement.SpaceEvenly,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        MyOutlineTextField(text = email, label = "Email", isPw = false)
//        MyOutlineTextField(text = pw, label = "Password", isPw = true)
//
//        OutlinedButton( onClick = { userVM.loginUser(email.value, pw.value)}) {
//            Text(text = "Login")
//        }
//    }
//}
fun login(email:String, pw:String, info: MutableState<String>){
    Firebase.auth
        .signInWithEmailAndPassword(email, pw)
        .addOnSuccessListener {
            info.value = "You are logged in with account ${it.user!!.email.toString()}"
            Log.d("************", "Logged in")
        }
        .addOnFailureListener() {
            Log.d("************", it.message.toString())
        }
}

//@Composable
//fun MyOutlineTextField(text: MutableState<String>, label: String, isPw: Boolean) {
//    OutlinedTextField(
//        value = text.value,
//        onValueChange = { text.value = it },
//        label = { Text(label) },
//        visualTransformation =
//        if(isPw)
//            PasswordVisualTransformation()
//        else
//            VisualTransformation.None
//    )
//}

@Composable
fun SignupView() {
    val fireStore = Firebase.firestore

//    var username by remember { mutableStateOf("")}
    var email by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    val user = User(email, password)

    Row() {
        OutlinedTextField(value = email, onValueChange = {email = it})
        OutlinedTextField(value = password, onValueChange = {password = it})

        Button(onClick = {
            fireStore
                .collection("users")
                .add(user)

            email = ""
            password = ""
//            alert (success)
//            then hide sign up, convert to login
        }) {
            Text("button")
        }

    }
}

@Composable
fun LoginViewOld() {
    var text by remember { mutableStateOf("")}
    val fAuth = Firebase.auth
    val fireStore = Firebase.firestore
    fAuth
//        .createUserWithEmailAndPassword("a@a.com", "passpass")
        .signInWithEmailAndPassword("a@a.com", "passpass")
        .addOnSuccessListener {
//            Log.d("************", "Logged in")
            fireStore
                .collection("newsfeed")
//                .document(it.user!!.uid)
//                .set( Feed("t","ttt") )
                .add( Feed("u","uuu") )

        }
        .addOnFailureListener() {
            Log.d("************", it.message.toString())
        }
    Text(text = fAuth.currentUser!!.email.toString())
}
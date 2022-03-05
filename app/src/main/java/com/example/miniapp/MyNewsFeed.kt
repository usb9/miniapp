package com.example.miniapp

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun NewsFeed() {
    val fireStore = Firebase.firestore

    var author by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }
    var searchAuthor by remember { mutableStateOf("") }
    var newsList by remember { mutableStateOf(listOf<String>()) }


    Column() {
        OutlinedTextField(value = author, onValueChange = { author = it })
        OutlinedTextField(value = text, onValueChange = { text = it })
        Button(onClick = {
            fireStore
                .collection("newsfeed")
                .add( Feed(author,text) )

            author = ""
            text = ""
        }) {
            Text("Send feed")
        }

        OutlinedTextField(value = searchAuthor, onValueChange = { searchAuthor = it })
        Button(onClick = {
            fireStore
                .collection("newsfeed")
                .whereEqualTo("author", searchAuthor)
                .get()
                .addOnSuccessListener {
//                    Log.d("***********", it.get("news").toString())  // nếu có document xác định
//                    for (doc in it) {
//                        Log.d("***********", doc.get("news").toString())
//                    }
                    var news = mutableListOf<String>()

                    for (doc in it) {
                        news.add( doc.get("news").toString() )
                    }
                    newsList = news

                }
        }) {
            Text("See feeds")
        }
        newsList.forEach {
            Text(text = it)
        }
    }
}
package com.example.miniapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun NewsFeed() {
    val fireStore = Firebase.firestore

    var author = remember { mutableStateOf("") }
    var text = remember { mutableStateOf("") }
    var searchAuthor = remember { mutableStateOf("") }
    var newsList by remember { mutableStateOf(listOf<String>()) }
    var statusPost by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyOutlineTextField(text = author, label = "Your nickname", isPw = false)
        MyOutlineTextField(text = text, label = "Your new feed", isPw = false)
        Row(Modifier.padding(top = 10.dp, bottom = 20.dp)) {
            Button(onClick = {
                fireStore
                    .collection("newsfeed")
                    .add(Feed(author.value, text.value))

                author.value = ""
                text.value = ""
                statusPost = true
            }) {
                Text("Post feed")
            }
        }
        if (statusPost) {
            Text(text = "Post is success")
        }

        MyOutlineTextField(text = searchAuthor, label = "Find via nickname", isPw = false)
        Row(Modifier.padding(top = 10.dp, bottom = 20.dp)) {
            Button(onClick = {
                fireStore
                    .collection("newsfeed")
                    .whereEqualTo("author", searchAuthor.value)
                    .get()
                    .addOnSuccessListener {
//                    Log.d("***********", it.get("news").toString())  // nếu có document xác định
//                    for (doc in it) {
//                        Log.d("***********", doc.get("news").toString())
//                    }
                        var news = mutableListOf<String>()

                        for (doc in it) {
                            news.add(doc.get("news").toString())
                        }
                        newsList = news
                    }
                searchAuthor.value = ""
            }) {
                Text("See feeds")
            }
        }
        newsList.forEach {
            Text(text = it)
        }
    }
}
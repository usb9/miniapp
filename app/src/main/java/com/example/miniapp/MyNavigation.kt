package com.example.miniapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation() {
    val navControl = rememberNavController()

    Scaffold( topBar = {TopNavBar(navControl)} ) {
        NavHost(navController = navControl, startDestination = "user") {
//            composable("user") { Text("user") }
//            composable("user") { MyUser() }
            composable("user") { LoginView() }
            composable("category") { Text("category") }
            composable("newsfeed") { NewsFeed() }
        }
    }
}

@Composable
fun TopNavBar(navC: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.my_main_color)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_user),
            contentDescription = "user",
            Modifier.clickable { navC.navigate("user") },
            tint = Color.White
        )
        Icon(
            painter = painterResource(R.drawable.ic_category),
            contentDescription = "category",
            Modifier.clickable { navC.navigate("category") },
            tint = Color.White
        )
        Icon(
            painter = painterResource(R.drawable.ic_newsfeed),
            contentDescription = "newsfeed",
            Modifier.clickable { navC.navigate("newsfeed") },
            tint = Color.White
        )
    }
}
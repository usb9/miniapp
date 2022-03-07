package com.example.miniapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation() {
    val userVM = viewModel<UserViewModel>()

    if(userVM.username.value.isEmpty()){
        MyUser(userVM)
    }else {
//        Text(text = userVM.username.value)
        MainScaffoldView(userVM.username.value, userVM.pws.value)
    }
}

@Composable
fun MainScaffoldView(username: String, pws: String) {
    val navControl = rememberNavController()

    Scaffold(
        topBar = { TopBarView() },
        bottomBar = { BottomNavBarView(navControl) },
//        content = {}
    ) {
        NavHost(navController = navControl, startDestination = "user") {
            composable("user") { MyAccount(username, pws) }
            composable("category") { MyProduct() }
            composable("newsfeed") { MyNewsFeed() }
        }
    }
}

@Composable
fun MyAccount(username: String, pws: String) {
    val showPsw = remember {mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
            .height(300.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text("Hello: $username")

        Button( onClick = { showPsw.value = true } ) {
            Text("Click here to see your password")
        }
        if (showPsw.value) {
            Text(text = pws)
        }
    }
}

@Composable
fun TopBarView() {
    val userVM = viewModel<UserViewModel>()

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFFFAB8FF))
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = userVM.username.value)
        OutlinedButton(onClick = { userVM.logoutUser() }) {
            Text(text = "Log out")
        }
    }
}

@Composable
fun BottomNavBarView(navC: NavHostController) {
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
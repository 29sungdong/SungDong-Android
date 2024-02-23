package com.example.sungdongwalk.activities.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sungdongwalk.components.navigator_bottom.BottomNav
import com.example.sungdongwalk.screens.WalkScreen
import com.example.sungdongwalk.viewmodels.WalkViewModel
import kotlinx.coroutines.launch

@Composable
fun Nav(){
    val navController = rememberNavController()

    rememberCoroutineScope().launch {
        WalkViewModel.instance.destination.collect{
            if(it.isNotEmpty()) {
                navController.navigate(Screens.WalkScreen.name)
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.name,
    ) {
        composable(route = Screens.MainScreen.name) {
            BottomNav(navController)
        }
        composable(route = Screens.WalkScreen.name) {
            WalkScreen(navController)
        }
    }

}

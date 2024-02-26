package com.example.sungdongwalk.activities.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sungdongwalk.components.navigator_bottom.BottomNav
import com.example.sungdongwalk.screens.BadgeScreen
import com.example.sungdongwalk.screens.FootprintScreen
import com.example.sungdongwalk.screens.MypageScreen
import com.example.sungdongwalk.screens.WalkScreen
import com.example.sungdongwalk.viewmodels.WalkViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
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
        composable(route = Screens.MypageScreen.name) {
            MypageScreen(navController)
        }
        composable(route = Screens.BadgeScreen.name) {
            BadgeScreen(navController)
        }
        composable(route = Screens.FootprintScreen.name) {
            FootprintScreen(navController)
        }
    }

}

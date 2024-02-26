package com.example.sungdongwalk.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.sungdongwalk.components.NavigatorTop
import com.example.sungdongwalk.components.NavigatorTopType
import com.example.sungdongwalk.components.event.EventContainer
import com.example.sungdongwalk.ui.theme.SDwhite

enum class EventCategory{
    ALL, EDUCATION, FARM, CULTURE, EXHIBITION, VOLUNTEER, PARK, FOREST
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventScreen(
    navController: NavController,
    setIsLoading: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SDwhite)
    ){
        NavigatorTop(NavigatorTopType.EVENT, navController)
        EventContainer(setIsLoading)
    }
}
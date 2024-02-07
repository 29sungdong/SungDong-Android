package com.example.sungdongwalk.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.sungdongwalk.R

data class NavItems(
    val label: String,
    val icon: Int,
    val route: String
)

val listOfNavItems = listOf(
    NavItems(
        label = "홈",
        icon = R.drawable.ic_home,
        route = Screens.HomeScreen.name
    ),
    NavItems(
        label = "산책",
        icon = R.drawable.ic_foot,
        route = Screens.WalkScreen.name
    ),
    NavItems(
        label = "행사",
        icon = R.drawable.ic_calendar,
        route = Screens.EventScreen.name
    ),
)
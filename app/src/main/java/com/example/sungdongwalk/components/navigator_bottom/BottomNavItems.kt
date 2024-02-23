package com.example.sungdongwalk.components.navigator_bottom

import com.example.sungdongwalk.R

data class BottomNavItems(
    val label: String,
    val icon: Int,
    val route: String
)

val listOfNavItems = listOf(
    BottomNavItems(
        label = "홈",
        icon = R.drawable.ic_home,
        route = BottomNavScreens.HomeScreen.name
    ),
    BottomNavItems(
        label = "산책",
        icon = R.drawable.ic_foot,
        route = BottomNavScreens.MapScreen.name
    ),
    BottomNavItems(
        label = "행사",
        icon = R.drawable.ic_calendar,
        route = BottomNavScreens.EventScreen.name
    ),
)
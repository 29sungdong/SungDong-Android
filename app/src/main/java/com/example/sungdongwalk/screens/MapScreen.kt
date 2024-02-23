package com.example.sungdongwalk.screens

import androidx.compose.runtime.Composable
import com.example.sungdongwalk.components.NavigatorTopSearch
import com.example.sungdongwalk.components.map.MapView

@Composable
fun MapScreen(setIsLoading: (Boolean)-> Unit) {
    MapView(setIsLoading)
    NavigatorTopSearch(setIsLoading)
}
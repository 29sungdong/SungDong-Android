package com.example.sungdongwalk.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.components.NavigatorTop
import com.example.sungdongwalk.components.NavigatorTopType
import com.example.sungdongwalk.components.calendar.Calendar
import com.example.sungdongwalk.ui.theme.SDwhite

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun EventScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SDwhite)
            .padding(10.dp)
    ) {
        NavigatorTop(NavigatorTopType.EVENT)
        Calendar()
    }
}
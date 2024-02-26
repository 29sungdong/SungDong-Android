package com.example.sungdongwalk.components.calendar

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.ui.theme.SDwhite
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(
    localDate: LocalDate,
    setLocalDate: (LocalDate) -> Unit,
    setIsShowModal: (Boolean) -> Unit,
    setSelectedDay: (Int) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SDwhite)
            .padding(start = 22.dp, end = 22.dp, bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalendarHeader(localDate, setLocalDate)
        CalendarMonthItem(localDate, setIsShowModal, setSelectedDay)
    }
}
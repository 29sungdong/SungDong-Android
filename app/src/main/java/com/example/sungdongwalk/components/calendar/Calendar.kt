package com.example.sungdongwalk.components.calendar

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.ui.theme.SDwhite
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendar(
    setIsShowWebsite: (Boolean) -> Unit
){
    val (localDate, setLocalDate) = rememberSaveable{
        mutableStateOf(LocalDate.now())
    }
    val (events, setEvents) = rememberSaveable{
        mutableStateOf<List<List<Dto.SimpleEventVo>>>((1..31).map{ listOf() })
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SDwhite)
            .padding(start = 22.dp, end = 22.dp, bottom = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalendarHeader(localDate, setLocalDate, setEvents)
        CalendarMonthItem(localDate, setIsShowWebsite, events)
    }
}
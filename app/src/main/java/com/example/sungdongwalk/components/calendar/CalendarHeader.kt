package com.example.sungdongwalk.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.R
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.api.retrofit.RetrofitManager
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.Typography
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarHeader(
    localDate: LocalDate,
    setLocalDate: (LocalDate) -> Unit,
    setEvents: (List<List<Dto.SimpleEventVo>>) -> Unit
){
    RetrofitManager.instance.getEventCalendar(
        localDate,
        setEvents,
        null)
    Row(
        modifier = Modifier.padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_prev),
            contentDescription = null,
            tint = Gray500,
            modifier = Modifier.clickable {
                val newLocalDate = localDate.minusMonths(1)
                RetrofitManager.instance.getEventCalendar(
                    newLocalDate,
                    setEvents,
                    setLocalDate)
            }
        )
        Text(
            text = "${localDate.monthValue}월",
            style = Typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal=10.dp)
            )
        Icon(
            painter = painterResource(id = R.drawable.ic_front),
            contentDescription = null,
            tint = Gray500,
            modifier = Modifier.clickable {
                val newLocalDate = localDate.plusMonths(1)
                RetrofitManager.instance.getEventCalendar(
                    newLocalDate,
                    setEvents,
                    setLocalDate)
            }
        )
    }
}
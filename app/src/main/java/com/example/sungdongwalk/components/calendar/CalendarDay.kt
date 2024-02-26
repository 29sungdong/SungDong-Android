package com.example.sungdongwalk.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.ui.theme.Gray400
import com.example.sungdongwalk.ui.theme.Gray700
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

enum class DayEventType{
    NO_EVENT, HAS_EVENT, FIN_EVENT
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDay(
    day: Int,
    events: List<List<Dto.SimpleEventVo>>,
    setIsShow: (Boolean) -> Unit,
    setSelectedDay: (Int) -> Unit
){
    val currDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
    val dayEventType =  if (day==0 || events[day-1].isEmpty()) DayEventType.NO_EVENT
                        else if(events[day - 1].maxOfOrNull { it.endDate }!! >= currDate) DayEventType.HAS_EVENT
                        else DayEventType.FIN_EVENT
    Column(
        modifier = Modifier
            .padding(10.dp)
            .background(SDwhite)
            .width(20.dp)
            .wrapContentHeight()
            .clickable {
                if (dayEventType != DayEventType.NO_EVENT) {
                    setSelectedDay(day)
                    setIsShow(true)
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "$day",
            style = Typography.titleMedium,
            color = if(day==0) Color.Transparent else Gray700,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(30.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Card(
            modifier = Modifier
                .size(15.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(
                containerColor = when(dayEventType){
                    DayEventType.NO_EVENT -> Color.Transparent
                    DayEventType.HAS_EVENT -> SDblue
                    DayEventType.FIN_EVENT -> Gray400
                }
            ),
        ) {

        }
    }
}
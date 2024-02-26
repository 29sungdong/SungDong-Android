package com.example.sungdongwalk.components.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.components.event.EventModal
import com.example.sungdongwalk.ui.theme.Gray200
import com.example.sungdongwalk.ui.theme.Gray400
import com.example.sungdongwalk.ui.theme.Gray600
import com.example.sungdongwalk.ui.theme.Gray700
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.Typography
import java.time.LocalDate

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarMonthItem(
    localDate: LocalDate,
    setIsShowWebsite: (Boolean) -> Unit,
    events: List<List<Dto.SimpleEventVo>>,
){
    val dayOfWeek = listOf("월", "화", "수", "목", "금", "토", "일")
    val startDayOfWeek = localDate.minusDays((localDate.dayOfMonth-1).toLong()).dayOfWeek.ordinal
    val endDayOfMonth = localDate.lengthOfMonth()
    val days = arrayListOf<Int>()
    val (isShowModal, setIsShowModal) = remember{
        mutableStateOf(false)
    }
    val (selectedDay, setSelectedDay) = remember{
        mutableStateOf(localDate.dayOfMonth)
    }
    repeat(startDayOfWeek){ days.add(0) }
    (1..endDayOfMonth).forEach{ days.add(it) }
    repeat ( (7 - (days.size) % 7)%7) { days.add(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            dayOfWeek.forEach{
                Text(
                    text = it,
                    style = Typography.labelLarge,
                    color = Gray700,
                    modifier = Modifier.padding(vertical= 10.dp)
                )
            }
        }
        Spacer(modifier = Modifier
            .height(2.dp)
            .background(Gray200)
            .fillMaxWidth())
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            maxItemsInEachRow = 7,
        ) {
            days.forEachIndexed { index, day ->
                CalendarDay(
                    day,
                    events,
                    setIsShowModal,
                    setSelectedDay
                )
                if((index+1)%7==0){
                    Spacer(modifier = Modifier
                        .height(2.dp)
                        .background(Gray200)
                        .fillMaxWidth())
                }
                }
            }
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            Card(
                modifier = Modifier
                    .size(15.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = SDblue
                )
            ){}
            Text(
                text = "행사 있는 날",
                style= Typography.labelLarge,
                color = Gray600,
                modifier = Modifier.padding(start = 7.dp, end=15.dp)
            )
            Card(
                modifier = Modifier
                    .size(15.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Gray400
                )
            ){}
            Text(
                text = "지난 행사",
                style= Typography.labelLarge,
                color = Gray600,
                modifier = Modifier.padding(horizontal = 7.dp)
            )
        }

    EventModal(date = "${localDate.monthValue}월 ${selectedDay}일", events[selectedDay-1], isShowModal, setIsShowModal,setIsShowWebsite)

}
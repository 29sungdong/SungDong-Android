package com.example.sungdongwalk.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.sungdongwalk.components.NavigatorTop
import com.example.sungdongwalk.components.NavigatorTopType
import com.example.sungdongwalk.components.event.EventContainer
import com.example.sungdongwalk.components.event.EventModal
import com.example.sungdongwalk.components.event.EventWebView
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.viewmodels.EventViewModel
import java.time.LocalDate

enum class EventCategory{
    ALL, EDUCATION, FARM, CULTURE, EXHIBITION, VOLUNTEER, PARK, FOREST
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventScreen(
    setIsLoading: (Boolean) -> Unit
) {
    val (localDate, setLocalDate) = remember{
        mutableStateOf(LocalDate.now())
    }
    val (selectedDay, setSelectedDay) = remember{
        mutableStateOf(localDate.dayOfMonth)
    }
    val (isShowModal, setIsShowModal) = remember{
        mutableStateOf(false)
    }
    val (isShowWebsite, setIsShowWebsite) = remember{
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SDwhite)
    ){
        NavigatorTop(NavigatorTopType.EVENT)
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(SDwhite),
//        ) {
//            item{
//                Calendar(localDate, setLocalDate, setIsShowModal, setSelectedDay)
//            }
//            item{
//                EventContainer(setIsLoading, setIsShowWebsite)
//            }
//        }
        EventContainer(localDate, setLocalDate, setIsShowModal, setSelectedDay, setIsLoading, setIsShowWebsite)
        EventModal(date = "${localDate.monthValue}월 ${selectedDay}일", EventViewModel.instance.events.value[selectedDay], isShowModal, setIsShowModal,setIsShowWebsite)
        EventWebView(isShowWebsite,setIsShowWebsite)
    }
}
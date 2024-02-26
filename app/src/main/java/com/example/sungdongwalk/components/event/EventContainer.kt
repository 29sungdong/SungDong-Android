package com.example.sungdongwalk.components.event

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.api.retrofit.RetrofitManager
import com.example.sungdongwalk.components.calendar.Calendar
import com.example.sungdongwalk.screens.EventCategory
import com.example.sungdongwalk.ui.theme.Lightblue100
import dev.chrisbanes.snapper.ExperimentalSnapperApi

@OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class,
    ExperimentalSnapperApi::class
)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventContainer(
    setIsLoading: (Boolean) -> Unit
){
    val (selectedCategory, setSelectedCategory) = remember{
        mutableStateOf(EventCategory.ALL)
    }
    val (events, setEvents) = remember{
        mutableStateOf<List<Dto.SimpleEventVo>>(listOf())
    }
    val (isShowWebsite, setIsShowWebsite) = remember{
        mutableStateOf(false)
    }
    remember(selectedCategory){
        val categoryName = if(selectedCategory == EventCategory.ALL) null else selectedCategory.name
        RetrofitManager.instance.getEvents(categoryName, null, setEvents)
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Lightblue100),
    ){
        item{
            Calendar(setIsShowWebsite)
        }
        stickyHeader {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Lightblue100)
                    .padding(top = 10.dp, bottom = 22.dp, start=10.dp, end=10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                maxItemsInEachRow = 4
            ){
                EventCategory.values().forEach{eventCategory->
                    EventCategoryItem(
                        selectedCategory = selectedCategory,
                        setSelectedCategory = setSelectedCategory,
                        eventCategory = eventCategory
                    )
                }
            }
        }
        itemsIndexed(events){ index, event ->
            EventItem(setIsLoading, event, setIsShowWebsite)
        }
    }
    EventWebView(isShowWebsite,setIsShowWebsite)

}
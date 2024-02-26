package com.example.sungdongwalk.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.R
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.components.CardLarge
import com.example.sungdongwalk.components.NavigatorTop
import com.example.sungdongwalk.components.NavigatorTopType
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
import com.example.sungdongwalk.viewmodels.PlaceViewModel
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(setIsLoading: (Boolean)->Unit) {
    val (places, setPlaces) = remember{
        mutableStateOf(listOf<Dto.SimplePlaceVo>())
    }
    rememberCoroutineScope().launch {
        PlaceViewModel.instance.places.collect(){
            if(it.isNotEmpty()) {
                setPlaces(it)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SDwhite)
    ) {
        NavigatorTop(type = NavigatorTopType.LOGO)
        Row(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ){
            Column {
                Row{
                    Text(
                        text = "주변 성동구 시설",
                        style = Typography.headlineLarge
                    )
                    Text(
                        text = "까지",
                        style = Typography.headlineMedium
                    )
                }
                Text(
                    text = "산책해볼까요?",
                    style = Typography.headlineMedium
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_list),
                contentDescription = "board",
                tint = Gray500,
                modifier = Modifier.padding(top=5.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ddg_default),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 50.dp)
                .align(Alignment.End)
                .size(60.dp))
        val pagerState = rememberPagerState(
            pageCount = {
                places.size
            }
        )

        HorizontalPager(
            state=pagerState,
            contentPadding = PaddingValues(horizontal=35.dp),
            pageSpacing = 20.dp,
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                pagerSnapDistance = PagerSnapDistance.atMost(10) // 한번에 몇 페이지까지 스크롤 가능하게 할 것인지
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){index->
            CardLarge(
                places[index],
                pagerState,
                index,
                setIsLoading
            )

        }
    }
}
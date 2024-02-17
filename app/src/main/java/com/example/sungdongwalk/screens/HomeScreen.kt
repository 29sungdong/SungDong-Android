package com.example.sungdongwalk.screens

import android.util.Log
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.R
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.components.CardLarge
import com.example.sungdongwalk.components.NavigatorTop
import com.example.sungdongwalk.components.NavigatorTopType
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SDwhite)
            .padding(10.dp)
    ) {
        NavigatorTop(type = NavigatorTopType.LOGO)
        Row(
            modifier = Modifier
                .padding(20.dp)
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
        )

        val placeList : List<Dto.SimplePlaceVo> = arrayListOf(
            Dto.SimplePlaceVo(
                id= 9,
                name="성동구립숲속도서관1",
                image="https://29sungdong.s3.ap-northeast-2.amazonaws.com/culture.jpeg",
                address= "서울 성동구 옥수동 528-6",
                tel= "02-2204-6485",
                openingTime= "7:00",
                closingTime= "22:00",
                xcoordinate= "127.011008",
                ycoordinate= "37.545854"
            ),
            Dto.SimplePlaceVo(
                id= 9,
                name="성동구립숲속도서관2",
                image="https://29sungdong.s3.ap-northeast-2.amazonaws.com/culture.jpeg",
                address= "서울 성동구 옥수동 528-6",
                tel= "02-2204-6485",
                openingTime= "7:00",
                closingTime= "22:00",
                xcoordinate= "127.011008",
                ycoordinate= "37.545854"
            ),
            Dto.SimplePlaceVo(
                id= 9,
                name="성동구립숲속도서관3",
                image="https://29sungdong.s3.ap-northeast-2.amazonaws.com/culture.jpeg",
                address= "서울 성동구 옥수동 528-6",
                tel= "02-2204-6485",
                openingTime= "7:00",
                closingTime= "22:00",
                xcoordinate= "127.011008",
                ycoordinate= "37.545854"
            ),
            Dto.SimplePlaceVo(
                id= 9,
                name="성동구립숲속도서관4",
                image="https://29sungdong.s3.ap-northeast-2.amazonaws.com/culture.jpeg",
                address= "서울 성동구 옥수동 528-6",
                tel= "02-2204-6485",
                openingTime= "7:00",
                closingTime= "22:00",
                xcoordinate= "127.011008",
                ycoordinate= "37.545854"
            )
        )

        HorizontalPager(
            pageCount = placeList.size,
            state=pagerState,
            contentPadding = PaddingValues(horizontal=35.dp),
            pageSpacing = 20.dp,
            beyondBoundsPageCount = 2, // lazy loading의 목적, 앞뒤에 몇 페이지를 미리 로드하는 최적화
            flingBehavior = PagerDefaults.flingBehavior(
                state = pagerState,
                pagerSnapDistance = PagerSnapDistance.atMost(2) // 한번에 몇 페이지까지 스크롤 가능하게 할 것인지
            )
        ){index->
            CardLarge(
                placeList[index],
                pagerState,
                index
            )

        }
    }
}
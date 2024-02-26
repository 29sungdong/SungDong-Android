package com.example.sungdongwalk.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sungdongwalk.R
import com.example.sungdongwalk.components.NavigatorTop
import com.example.sungdongwalk.components.NavigatorTopType
import com.example.sungdongwalk.components.PercentSlider
import com.example.sungdongwalk.ui.theme.Gray300
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.Lightblue100
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
import com.example.sungdongwalk.viewmodels.PlaceViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FootprintScreen(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SDwhite),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        NavigatorTop(type = NavigatorTopType.MYPAGE, navController = navController, "성동 발자국 지도")
        PercentSlider("지도달성률", PlaceViewModel.instance.userPlaceAchievementRate.value)
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 20.dp)
                .verticalScroll(rememberScrollState()),
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            PlaceViewModel.instance.userPlace.value.forEachIndexed { index, it ->
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = if(it.isVisited) Lightblue100 else Gray300
                    ),
                    modifier = Modifier
                        .size(100.dp)
                        .align(CenterVertically)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ){
                        if (it.isVisited)
                            Icon(
                                painter = painterResource(id = R.drawable.ic_footprint),
                                contentDescription = null,
                                tint = SDblue,
                                modifier = Modifier
                                    .padding(12.dp)
                                    .align(Center)
                            )
                        Text(
                            text = it.name,
                            color = if (it.isVisited) SDblack else Gray500,
                            textAlign = TextAlign.Center,
                            style = Typography.labelLarge,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Center)
                        )
                    }
                }
            }
        }
    }
}
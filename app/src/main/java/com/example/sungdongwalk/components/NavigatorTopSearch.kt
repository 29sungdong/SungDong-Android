package com.example.sungdongwalk.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.R
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.api.retrofit.RetrofitManager
import com.example.sungdongwalk.ui.theme.DimLight
import com.example.sungdongwalk.ui.theme.Gray600
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
import com.example.sungdongwalk.viewmodels.LocationViewModel


@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable 
fun NavigatorTopSearch(setIsLoading: (Boolean)-> Unit){
    val (text, setText) = remember{
        mutableStateOf("")
    }
    val (results, setResults) = remember{
        mutableStateOf(listOf<Dto.MarkerVo>())
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (results.isNotEmpty()) DimLight else Color.Transparent)
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 6.dp, bottomEnd = 6.dp),
            colors = CardDefaults.cardColors(
                containerColor = SDwhite,
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                if(text != "") {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back",
                        tint = SDblue,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable {
                                keyboardController?.hide()
                            }
                    )

                    Spacer(modifier = Modifier.size(15.dp))
                }
                SearchBarWalk(text=text, setText=setText, setResults=setResults)
            }

            LazyColumn(
                modifier = Modifier
                    .background(SDwhite)
                    .fillMaxWidth()
            ){
                items(results.size){ idx ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                keyboardController?.hide()
                                setIsLoading(true)
                                RetrofitManager.instance.getShortestPath(
                                    placeId = results[idx].id,
                                    xCoordinate = LocationViewModel.instance.location.value.longitude.toString(),
                                    yCoordinate = LocationViewModel.instance.location.value.latitude.toString(),
                                )
                                RetrofitManager.instance.getPaths(
                                    placeId = results[idx].id,
                                    placeName = results[idx].name,
                                    placeImg = results[idx].image,
                                    xCoordinate = LocationViewModel.instance.location.value.longitude.toString(),
                                    yCoordinate = LocationViewModel.instance.location.value.latitude.toString(),
                                )
                            }
                    ) {
                        Text(
                            text= results[idx].name,
                            style = Typography.labelMedium,
                            color = Gray600,
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 20.dp, start = 60.dp, end = 20.dp)
                        )
                    }
                }
            }

            if(results.isNotEmpty())
                Spacer(Modifier.height(20.dp))
        }
    }
}
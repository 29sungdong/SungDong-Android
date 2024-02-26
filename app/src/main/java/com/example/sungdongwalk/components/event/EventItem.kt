package com.example.sungdongwalk.components.event

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.sungdongwalk.ui.theme.Gray300
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.Lightblue100
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
import com.example.sungdongwalk.viewmodels.EventViewModel
import com.example.sungdongwalk.viewmodels.LocationViewModel

@Composable
fun EventItem(
    setIsLoading: (Boolean) -> Unit,
    eventVo: Dto.SimpleEventVo,
    setIsShowWebsite: (Boolean) -> Unit
){
    val date = eventVo.endDate.split('/',' ')
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Lightblue100)
            .padding(start=15.dp, end = 15.dp, bottom = 15.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = SDwhite
        )
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp),
            text = eventVo.name,
            style = Typography.titleLarge,
            textAlign = TextAlign.Start,
            color = SDblack
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, end = 20.dp),
            text = "${date[0]}월 ${date[1]}일 ${date[2]}까지",
            style = Typography.bodyLarge,
            textAlign = TextAlign.End,
            color = Gray500
        )
        Spacer(
            Modifier
                .height(1.dp)
                .fillMaxWidth(0.93f)
                .align(Alignment.CenterHorizontally)
                .background(Gray300)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 17.dp)
                    .clickable {
                        setIsLoading(true)
                        RetrofitManager.instance.getShortestPath(
                            placeId = eventVo.placeId,
                            xCoordinate = LocationViewModel.instance.location.value.longitude.toString(),
                            yCoordinate = LocationViewModel.instance.location.value.latitude.toString(),
                        )
                        RetrofitManager.instance.getPaths(
                            placeId = eventVo.placeId,
                            placeName = eventVo.placeName,
                            placeImg = "",//eventVo.image,
                            xCoordinate = LocationViewModel.instance.location.value.longitude.toString(),
                            yCoordinate = LocationViewModel.instance.location.value.latitude.toString(),
                        )
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = null,
                    tint= Gray500
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text= eventVo.placeName,
                    style= Typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(
                Modifier
                    .width(1.dp)
                    .height(45.dp)
                    .background(Gray300)
                    .align(Alignment.CenterVertically)
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 17.dp)
                    .clickable {
                        setIsShowWebsite(true)
                        EventViewModel.instance.updateUrl(eventVo.url)
                    },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_link),
                    contentDescription = null,
                    tint= Gray500
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text="자세히 보기",
                    style= Typography.labelMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
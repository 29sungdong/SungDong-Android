package com.example.sungdongwalk.components

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.rememberImagePainter
import com.example.sungdongwalk.R
import com.example.sungdongwalk.api.Dto.SimplePlaceVo
import com.example.sungdongwalk.api.retrofit.RetrofitManager
import com.example.sungdongwalk.ui.theme.Gray300
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.SDgreen
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
import com.example.sungdongwalk.viewmodels.LocationViewModel
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardLarge(
    placeVo: SimplePlaceVo,
    pagerState: PagerState,
    index: Int,
    setIsLoading: (Boolean) -> Unit
){
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = SDwhite,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                val pageOffset = (
                        (pagerState.currentPage - index) + pagerState
                            .currentPageOffsetFraction // 선택된 페이지에서 페이지까지의 거리를 확인
                        ).absoluteValue

                // We animate the alpha, between 50% and 100%
                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
        ,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = Uri.parse(placeVo.image)),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            contentScale = ContentScale.FillWidth
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Start,
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null,
                tint = SDgreen
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column{
                Text(
                    text=placeVo.name,
                    style= Typography.titleLarge,
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = placeVo.address,
                    style = Typography.bodyMedium,
                    color = Gray500,
                )
            }

        }
        Spacer(
            Modifier
                .height(1.dp)
                .fillMaxWidth(0.9f)
                .align(CenterHorizontally)
                .background(Gray300)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(0.5f)
                    .clickable { },
                horizontalAlignment = CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = null,
                    tint= Gray500
                )
                Text(
                    text="${placeVo.openingTime}-${placeVo.closingTime}",
                    style= Typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(
                Modifier
                    .width(1.dp)
                    .height(70.dp)
                    .background(Gray300)
                    .align(CenterVertically)
            )
            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .clickable { },
                horizontalAlignment = CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_call),
                    contentDescription = null,
                    tint= Gray500
                )
                Text(
                    text= placeVo.tel.ifBlank { "-" },
                    style= Typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
        Button(
            buttonSize = ButtonSize.MAXWIDTH,
            buttonText = "산책 시작하기",
            buttonOnClick = {
                setIsLoading(true)
                RetrofitManager.instance.getShortestPath(
                    placeId = placeVo.id,
                    xCoordinate = LocationViewModel.instance.location.value.longitude.toString(),
                    yCoordinate = LocationViewModel.instance.location.value.latitude.toString(),
                )
                RetrofitManager.instance.getPaths(
                    placeId = placeVo.id,
                    placeName = placeVo.name,
                    placeImg = placeVo.image,
                    xCoordinate = LocationViewModel.instance.location.value.longitude.toString(),
                    yCoordinate = LocationViewModel.instance.location.value.latitude.toString(),
                )
            }
        )

    }
}
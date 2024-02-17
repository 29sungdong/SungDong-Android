package com.example.sungdongwalk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.R
import com.example.sungdongwalk.ui.theme.Lightblue200
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

enum class NavigatorTopWalkType {
    WALK, ARRIVE, MAP
}
@Composable
fun NavigatorTopWalk(type: NavigatorTopWalkType, destination: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(SDwhite),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 10.dp, bottomEnd = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = SDblue,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Text(
                modifier = Modifier
                    .padding(vertical=25.dp),
                text = if(type==NavigatorTopWalkType.MAP) "시설지도" else "목적지",
                style = Typography.labelLarge,
                color = SDwhite
            )
            Card(
                shape = RoundedCornerShape(4.dp),
            ){
                Text(
                    text = destination,
                    style = Typography.labelLarge,
                    color = SDwhite,
                    modifier = Modifier
                        .background(if (type == NavigatorTopWalkType.MAP) SDblue else Lightblue200)
                        .fillMaxWidth(0.65f)
                        .padding(7.dp),
                )
            }
            Card(
                shape = RoundedCornerShape(5.dp),
            ){
                when(type){
                    NavigatorTopWalkType.MAP -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_x_small),
                            contentDescription = "close",
                            tint = SDwhite,
                            modifier = Modifier
                                .background(SDblue)
                        )
                    }
                    else -> Text(
                        text = if(type==NavigatorTopWalkType.WALK) "산책진행중" else "목적지도착",
                        style = Typography.bodyLarge,
                        color = if(type==NavigatorTopWalkType.WALK) SDblue else SDwhite,
                        modifier = Modifier
                            .background(if(type==NavigatorTopWalkType.WALK) SDwhite else SDblue)
                            .border(
                                width = 1.dp,
                                color = if(type==NavigatorTopWalkType.WALK) SDblue else SDwhite,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(7.dp),
                    )
                }
            }
        }
    }
}
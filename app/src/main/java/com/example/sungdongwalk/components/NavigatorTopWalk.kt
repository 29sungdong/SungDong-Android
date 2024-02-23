package com.example.sungdongwalk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sungdongwalk.R
import com.example.sungdongwalk.activities.navigation.Screens
import com.example.sungdongwalk.ui.theme.Lightblue200
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

enum class WalkStateType {
    PREPARE, WALK, ARRIVE, MAP
}
@Composable
fun NavigatorTopWalk(navController: NavController, walkState: WalkStateType, destination: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 10.dp, bottomEnd = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = SDblue,
        ),
        elevation = CardDefaults.elevatedCardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 7.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Text(
                modifier = Modifier
                    .padding(vertical=25.dp),
                text = if(walkState==WalkStateType.MAP) "시설지도" else "목적지",
                style = Typography.labelLarge,
                color = SDwhite,
            )
            Card(
                shape = RoundedCornerShape(4.dp),
            ){
                Text(
                    text = destination,
                    style = Typography.labelLarge,
                    color = SDwhite,
                    modifier = Modifier
                        .background(if (walkState == WalkStateType.MAP) SDblue else Lightblue200)
                        .fillMaxWidth(0.65f)
                        .padding(7.dp),
                )
            }
            Card(
                shape = RoundedCornerShape(5.dp),
            ){
                when(walkState){
                    WalkStateType.MAP -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_x_small),
                            contentDescription = "close",
                            tint = SDwhite,
                            modifier = Modifier
                                .background(SDblue)
                                .clickable {
                                    navController.navigate(Screens.MainScreen.name){
                                        popUpTo(0)
                                    }
                                }
                        )
                    }
                    else -> Text(
                        text = if(walkState==WalkStateType.ARRIVE) "목적지도착" else "산책진행중",
                        style = Typography.bodyLarge,
                        color = if(walkState==WalkStateType.ARRIVE) SDwhite else SDblue,
                        modifier = Modifier
                            .background(if (walkState == WalkStateType.ARRIVE) SDblue else SDwhite)
                            .border(
                                width = 1.dp,
                                color = if (walkState == WalkStateType.ARRIVE) SDwhite else SDblue,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .padding(7.dp),
                    )
                }
            }
        }
    }
}
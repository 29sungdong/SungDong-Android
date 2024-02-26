package com.example.sungdongwalk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.ui.theme.Gray100
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

@Composable
fun PercentSlider(
    title : String,
    percent : Int,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            .background(SDwhite)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 7.dp, end = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = title, color = SDblue, style = Typography.bodyLarge)
            Text(text = "$percent%", color = SDblack, style = Typography.bodyLarge)
        }
        LinearProgressIndicator(
            progress = percent*0.01f,
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
                .clip(CircleShape),
            trackColor = Gray100,
            color = SDblue,
        )

    }
}
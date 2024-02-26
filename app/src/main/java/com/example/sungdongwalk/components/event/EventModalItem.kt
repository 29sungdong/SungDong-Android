package com.example.sungdongwalk.components.event

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.R
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.ui.theme.Gray200
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.Lightblue100
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.Typography
import com.example.sungdongwalk.viewmodels.EventViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EventModalItem(
    eventVo: Dto.SimpleEventVo,
    setIsShowWebsite: (Boolean) -> Unit
){
    val endDateList = eventVo.endDate.split('/',' ')
    val currDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                setIsShowWebsite(true)
                EventViewModel.instance.updateUrl(eventVo.url)
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = if(currDate <= eventVo.endDate) Lightblue100 else Gray200
        )
    ) {
        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column{
                androidx.compose.material3.Text(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    text = eventVo.name,
                    style = Typography.titleMedium,
                    textAlign = TextAlign.Start,
                    color = SDblack
                )
                androidx.compose.material3.Text(
                    text = "${eventVo.placeName}·${endDateList[1]}월 ${endDateList[2]}일 ${endDateList[3]}까지",
                    style = Typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    color = Gray500
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_link),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
                tint = Gray500
            )
        }
    }
}
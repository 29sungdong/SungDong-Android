package com.example.sungdongwalk.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.R
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
import com.example.sungdongwalk.viewmodels.WalkViewModel

enum class OptionType{
    SHORTEST, WALK_TRAIL
}
@Composable
fun ButtonBoarder(
    selectedId: Int,
    setSelectedId: (Int) -> Unit,
    id: Int,
    type: OptionType,
    name: String,
    totalDistance : Int,
    totalTime : Int
){
    androidx.compose.material3.Button(
        modifier = Modifier
            .wrapContentWidth()
            .padding(
                start = if (id == 0) 20.dp else 10.dp,
                end = if (id == WalkViewModel.instance.paths.value.size) 20.dp else 0.dp
            ),
        contentPadding = PaddingValues(25.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = SDwhite,
        ),
        shape = RoundedCornerShape(6.dp),
        border=BorderStroke(width=2.dp,color=if(selectedId==id) SDblue else Gray500),
        onClick = {
            setSelectedId(id)
        }
    ) {
        Image(painter = painterResource(
            id = if(type==OptionType.SHORTEST) R.drawable.ic_walk else R.drawable.ic_tree),
            contentDescription = null,
            colorFilter = ColorFilter.tint(if(selectedId==id) SDblue else Gray500),
            modifier = Modifier.padding(bottom = 45.dp)
        )
        Column{
            Text(
                text = name,
                color = if(selectedId == id) SDblue else Gray500,
                style = Typography.titleLarge,
                modifier = Modifier.padding(horizontal = 5.dp)
            )
            Text(
                text = "${totalTime/3600}시간 ${totalTime/60%60}분",
                color = if(selectedId == id) SDblue else Gray500,
                style = Typography.headlineLarge,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}
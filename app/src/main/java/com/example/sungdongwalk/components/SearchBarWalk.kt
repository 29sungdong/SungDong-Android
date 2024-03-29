package com.example.sungdongwalk.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.R
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.api.retrofit.RetrofitManager
import com.example.sungdongwalk.ui.theme.Gray200
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.Red
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarWalk(
    text: String,
    setText: (String) -> Unit,
    setResults: (List<Dto.MarkerVo>) -> Unit
){
    TextField(
        value = text,
        onValueChange ={
            setText(it)
            RetrofitManager.instance.getPlacesSearch(it, setResults)
                       },
        shape = RoundedCornerShape(4.dp),
        textStyle = Typography.labelMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(Color.Transparent),
        colors = TextFieldDefaults.textFieldColors(
            textColor = SDblack,
            containerColor = Gray200,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Red,
            placeholderColor = Gray500,
        ),
        singleLine = true,
        placeholder = {Text(text="가고싶은 시설을 입력해주세요")},
        trailingIcon = {
            when(text){
                "" -> Icon(
                    painterResource(id = R.drawable.ic_search),
                    contentDescription = "search",
                    tint = SDblue,
                )
                else -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            painterResource(id = R.drawable.ic_circle_x),
                            contentDescription = "circle_x",
                            modifier = Modifier
                                .clickable {
                                    setResults(listOf())
                                    setText("")
                                           },
                            tint = Gray500
                        )
                        Icon(
                            painterResource(id = R.drawable.ic_x),
                            contentDescription = null,
                            tint = SDwhite
                        )
                    }
                }
            }
        }
    )

}
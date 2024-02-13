package com.example.sungdongwalk.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.ui.theme.Gray100
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.Red
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

enum class TextFieldType{
    LONG, SHORT
}
enum class TextColorType{
    LIGHT, DARK
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Textfield(
    text: String,
    setText: (String)->Unit,
    textColorType: TextColorType,
    textFieldType: TextFieldType
){
    TextField(
        value = text,
        onValueChange = setText,
        textStyle = Typography.bodySmall,
        modifier = Modifier
            .fillMaxWidth(if(textFieldType==TextFieldType.SHORT)0.6f else 1f)
            .background(Color.Transparent)
            .padding(5.dp),
        singleLine = true,
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = if(textColorType==TextColorType.LIGHT) Gray500 else SDblack,
            containerColor = Gray100,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Red,
        )
    )
}
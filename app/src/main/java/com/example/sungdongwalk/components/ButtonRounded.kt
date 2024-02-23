package com.example.sungdongwalk.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

@Composable
fun ButtonRounded(
    buttonText: String,
    buttonOnClick: ()->Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        androidx.compose.material3.Button(
            shape = RoundedCornerShape(30.dp),
            onClick = buttonOnClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = SDblue,
                contentColor = SDwhite
            ),
            modifier = Modifier
                .wrapContentSize(),
            elevation = ButtonDefaults.buttonElevation(2.dp)
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = buttonText,
                style = Typography.titleLarge,
                color = SDwhite
            )
        }
    }
}
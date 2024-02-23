package com.example.sungdongwalk.components
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

enum class ButtonSize{
    SMALL, MEDIUM, LARGE, MAXWIDTH
}
@Composable
fun Button(
    buttonSize: ButtonSize,
    buttonText: String,
    buttonOnClick:  ()  -> Unit
){
    Button(
        onClick = buttonOnClick,
        shape = RoundedCornerShape(if(buttonSize==ButtonSize.MAXWIDTH) 0.dp else 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = SDblue,
            disabledContainerColor = Gray500,
            contentColor = SDwhite,
        ),
        contentPadding = PaddingValues(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (buttonSize == ButtonSize.MAXWIDTH) 0.dp else 20.dp)
    ) {
        Text(
            text = buttonText,
            modifier = Modifier.fillMaxWidth(),
            style = Typography.titleLarge,
            textAlign = TextAlign.Center
        )
    }
}
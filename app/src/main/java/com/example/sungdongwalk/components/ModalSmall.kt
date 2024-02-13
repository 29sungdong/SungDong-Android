package com.example.sungdongwalk.components



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.ui.theme.DimDark
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

@Composable
fun ModalSmall(
    modalText: String,
    modalOnClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DimDark),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .background(color = SDwhite, shape = RoundedCornerShape(12.dp))
                .align(CenterHorizontally)
                .padding(vertical = 20.dp),
        ) {
            Text(
                text = modalText,
                style = Typography.headlineSmall,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(bottom=60.dp, top=40.dp)
            )
            Button(
                buttonSize = ButtonSize.SMALL,
                buttonText = "확인",
                buttonOnClick = modalOnClick
            )
        }
    }
}
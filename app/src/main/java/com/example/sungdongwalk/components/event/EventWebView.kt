package com.example.sungdongwalk.components.event

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.sungdongwalk.R
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
import com.example.sungdongwalk.viewmodels.EventViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
@Composable
fun EventWebView(
    isShowWebSite: Boolean,
    setIsShowWebsite: (Boolean) -> Unit
){

    val state = rememberWebViewState(url = EventViewModel.instance.url.value)

    if(isShowWebSite){
        Dialog(
            onDismissRequest = { setIsShowWebsite(false) },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SDwhite
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "성동구 공공서비스 예약",
                        style = Typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(15.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_x_small),
                        contentDescription = null,
                        modifier = Modifier
                            .clickable { setIsShowWebsite(false) }
                            .wrapContentSize()
                            .background(Color.Transparent)
                            .padding(15.dp)
                    )
                }
                WebView(
                    state = state,
                    modifier = Modifier
                        .fillMaxSize(),
                    client = BaseWebViewClient()
                )
            }
        }
    }

}
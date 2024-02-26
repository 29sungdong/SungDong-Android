package com.example.sungdongwalk.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sungdongwalk.R
import com.example.sungdongwalk.activities.navigation.Screens
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

enum class NavigatorTopType {
    LOGO, EVENT, MYPAGE
}
@Composable
fun NavigatorTop(
    type: NavigatorTopType,
    navController: NavController,
    navText: String? = null
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(22.dp)
            .background(color = SDwhite),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        when(type){
            NavigatorTopType.LOGO -> Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                contentScale = ContentScale.Crop,
            )
            NavigatorTopType.EVENT -> Text(
                text = "행사",
                style = Typography.headlineLarge
            )
            NavigatorTopType.MYPAGE -> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ){
                    Icon(
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { navController.navigateUp()  },
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back",
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = navText!!,
                        style = Typography.headlineLarge,
                    )
                }

            }
        }
        if(type != NavigatorTopType.MYPAGE ){
            Icon(
                modifier = Modifier.clickable { navController.navigate(Screens.MypageScreen.name)},
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "profile",
                tint = Gray500,
            )
        }
    }
}
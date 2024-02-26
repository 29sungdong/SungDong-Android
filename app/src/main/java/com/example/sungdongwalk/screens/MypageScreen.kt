package com.example.sungdongwalk.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sungdongwalk.R
import com.example.sungdongwalk.activities.navigation.Screens
import com.example.sungdongwalk.components.NavigatorTop
import com.example.sungdongwalk.components.NavigatorTopType
import com.example.sungdongwalk.ui.theme.Gray100
import com.example.sungdongwalk.ui.theme.Gray300
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
import com.example.sungdongwalk.viewmodels.LoginViewModel

@Composable
fun MypageScreen(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SDwhite),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column{
            NavigatorTop(type = NavigatorTopType.MYPAGE, navController = navController, "내 정보")
            Column(
                modifier = Modifier
                    .padding(horizontal = 22.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top=38.dp, bottom = 60.dp, start = 35.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_profile_big),
                        contentDescription = null,
                        modifier = Modifier
                            .size(110.dp)
                            .padding(end = 20.dp)
                    )
                    Column(
                        modifier = Modifier
                            .height(90.dp),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = LoginViewModel.instance.nickname.value,
                                style = Typography.titleLarge
                            )
                            Text(
                                text = "님",
                                style = Typography.titleLarge,
                                color = Gray500,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                        Card(
                            shape = RoundedCornerShape(4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Gray100
                            ),
                            modifier = Modifier
                                .clickable { }
                        ) {
                            Text(
                                text = "프로필 수정",
                                style = Typography.labelLarge,
                                color = Gray500,
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp)
                            )
                        }
                    }
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Gray300)
                )
                Column(
                    Modifier.padding(top = 10.dp)
                ) {
                    Text(
                        text = "내 뱃지",
                        style = Typography.titleMedium,
                        modifier = Modifier
                            .padding(vertical = 20.dp, horizontal = 10.dp)
                            .fillMaxWidth()
                            .clickable { navController.navigate(Screens.BadgeScreen.name) }
                    )
                    Text(
                        text = "성동 발자국 지도",
                        style = Typography.titleMedium,
                        modifier = Modifier
                            .padding(vertical = 20.dp, horizontal = 10.dp)
                            .fillMaxWidth()
                            .clickable { navController.navigate(Screens.FootprintScreen.name) }
                    )
                    Text(
                        text = "공지사항",
                        style = Typography.titleMedium,
                        modifier = Modifier
                            .padding(vertical = 20.dp, horizontal = 10.dp)
                            .fillMaxWidth()
                    )
                    Text(
                        text = "시설 정보 추가 요청",
                        style = Typography.titleMedium,
                        modifier = Modifier
                            .padding(vertical = 20.dp, horizontal = 10.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom=30.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "로그아웃",
                style = Typography.labelLarge,
                modifier = Modifier
                    .padding(20.dp)
            )
            Text(
                text = "회원탈퇴",
                style = Typography.labelLarge,
                color = Gray500,
                modifier = Modifier
                    .padding(20.dp)
            )
        }
    }
}
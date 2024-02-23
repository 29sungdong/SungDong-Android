package com.example.sungdongwalk.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.sungdongwalk.R
import com.example.sungdongwalk.activities.navigation.Screens
import com.example.sungdongwalk.ui.theme.DimDark
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
import com.example.sungdongwalk.viewmodels.WalkViewModel

@Composable
fun DimArrive(
    navController: NavController,
    setWalkState: (WalkStateType) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DimDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_particle),
                contentDescription = null,

            )
            Column(
                modifier = Modifier
                    .fillMaxSize(0.7f)
                    .padding(top = 150.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "${WalkViewModel.instance.destination.value[1]}\n방문 완료!",
                    style = Typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = SDwhite,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    shape = CircleShape
                ) {
                    Image(
                        painter = rememberImagePainter(data = Uri.parse(WalkViewModel.instance.destination.value[2])),
                        contentDescription = null,
                        modifier = Modifier
                            .size(170.dp),
                        contentScale = ContentScale.FillHeight
                    )
                }

            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        ){
            Button(
                buttonSize = ButtonSize.LARGE,
                buttonText = "시설지도 입장하기",
                buttonOnClick = {
                setWalkState(WalkStateType.MAP)
                }
            )
            androidx.compose.material3.Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(Screens.MainScreen.name){
                        popUpTo(0)
                    }
                }
            ) {
                Text(
                    text = "산책 종료하기",
                    style = Typography.labelLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(15.dp)
                )
            }
        }
    }
}
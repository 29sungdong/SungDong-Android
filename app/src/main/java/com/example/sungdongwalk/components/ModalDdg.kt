package com.example.sungdongwalk.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.sungdongwalk.ui.theme.Gray300
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
@Composable
fun ModalDdg(
    modalText: String,
    confirmText: String,
    cancelText: String,
    confirmOnClick: () -> Unit,
    cancelOnClick: () -> Unit,
    resourceId: Int
){
    val (isShow, setIsShow) = remember{
        mutableStateOf(true)
    }
    if(isShow) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 25.dp),
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = true,
                decorFitsSystemWindows = true
            ),
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Gray300,
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    androidx.compose.material3.Button(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .fillMaxWidth(0.45f),
                        onClick = confirmOnClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SDwhite,
                            contentColor = SDblack,
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = confirmText,
                            style = Typography.titleLarge,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                    androidx.compose.material3.Button(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 20.dp)
                            .fillMaxWidth(),
                        onClick = cancelOnClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SDwhite,
                            contentColor = SDblack,
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = cancelText,
                            style = Typography.titleLarge,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            },

            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = modalText,
                        style = Typography.titleLarge,
                        textAlign = TextAlign.Center
                    )
                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 30.dp, bottom = 10.dp)
                    )
                }
            },
            onDismissRequest = {
                setIsShow(false)
            }
        )
    }
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = DimDark),
//        verticalArrangement = Arrangement.Center
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth(0.8f)
//                .background(color = Gray300, shape = RoundedCornerShape(12.dp))
//                .align(CenterHorizontally)
//                .padding(vertical = 25.dp),
//        ) {
//            Text(
//                text = modalText,
//                style = Typography.titleLarge,
//                modifier = Modifier
//                    .align(CenterHorizontally)
//            )
//            Image(
//                painter = painterResource(id = resourceId),
//                contentDescription = null,
//                modifier = Modifier
//                    .align(CenterHorizontally)
//                    .padding(vertical = 30.dp)
//                    .size(70.dp)
//            )
//            Row (
//                modifier = Modifier
//                    .align(CenterHorizontally)
//                    .fillMaxWidth(),
//            ){
//                Button(
//                    modifier = Modifier
//                        .padding(start=20.dp)
//                        .fillMaxWidth(0.45f),
//                    onClick = confirmOnClick,
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = SDwhite,
//                        contentColor = SDblack,
//                    ),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(
//                        text = confirmText,
//                        style= Typography.titleMedium,
//                        modifier = Modifier.fillMaxWidth(),
//                        textAlign = TextAlign.Center
//                    )
//                }
//                Button(
//                    modifier = Modifier
//                        .padding(start=10.dp,end=20.dp)
//                        .fillMaxWidth(),
//                    onClick = cancelOnClick,
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = SDwhite,
//                        contentColor = SDblack,
//                    ),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Text(
//                        text = cancelText,
//                        style= Typography.titleMedium,
//                        modifier = Modifier.fillMaxWidth(),
//                        textAlign = TextAlign.Center
//                    )
//                }
//            }
//        }
//    }
}
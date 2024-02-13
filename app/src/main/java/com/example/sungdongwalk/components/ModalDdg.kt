package com.example.sungdongwalk.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.R
import com.example.sungdongwalk.ui.theme.DimDark
import com.example.sungdongwalk.ui.theme.Gray300
import com.example.sungdongwalk.ui.theme.Typography
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.SDwhite

@Composable
fun ModalDdg(
    modalText: String,
    confirmText: String,
    cancelText: String,
    confirmOnClick: () -> Unit,
    cancelOnClick: () -> Unit,
    resourceId: Int
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DimDark),
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(color = Gray300, shape = RoundedCornerShape(12.dp))
                .align(CenterHorizontally)
                .padding(vertical = 25.dp),
        ) {
            Text(
                text = modalText,
                style = Typography.titleLarge,
                modifier = Modifier
                    .align(CenterHorizontally)
            )
            Image(
                painter = painterResource(id = resourceId),
                contentDescription = null,
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(vertical = 30.dp)
                    .size(65.dp)
            )
            Row (
                modifier = Modifier.align(CenterHorizontally),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.38f)
                        .padding(5.dp),
                    onClick = confirmOnClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SDwhite,
                        contentColor = SDblack,
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = confirmText,
                        style= Typography.titleMedium
                    )
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(5.dp),
                    onClick = cancelOnClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = SDwhite,
                        contentColor = SDblack,
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = cancelText,
                        style= Typography.titleMedium
                    )
                }
            }
        }
    }
}
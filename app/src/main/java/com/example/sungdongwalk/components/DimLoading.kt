package com.example.sungdongwalk.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sungdongwalk.R
import com.example.sungdongwalk.ui.theme.DimLight
import com.example.sungdongwalk.viewmodels.WalkViewModel

@Preview(showBackground = true)
@Composable
fun DimLoading(){
    WalkViewModel.instance.initDestination()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DimLight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ddg_loading), contentDescription = null)
    }
}
package com.example.sungdongwalk.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sungdongwalk.activities.navigation.Screens
import com.example.sungdongwalk.components.Button
import com.example.sungdongwalk.components.ButtonBoarder
import com.example.sungdongwalk.components.ButtonRounded
import com.example.sungdongwalk.components.ButtonSize
import com.example.sungdongwalk.components.DimArrive
import com.example.sungdongwalk.components.NavigatorTopWalk
import com.example.sungdongwalk.components.OptionType
import com.example.sungdongwalk.components.WalkStateType
import com.example.sungdongwalk.components.map.PathView
import com.example.sungdongwalk.viewmodels.WalkViewModel

@Composable
fun WalkScreen(
    navController: NavController,
){
    val (selectedId, setSelectedId) = remember{
        mutableStateOf(0)
    }
    val (walkState, setWalkState) = remember{
        mutableStateOf(WalkStateType.PREPARE)
    }

    PathView(type= walkState, setType = setWalkState, selectedId = selectedId)
    if(walkState==WalkStateType.ARRIVE){
        DimArrive(
            navController = navController,
            setWalkState
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ){
        NavigatorTopWalk(navController=navController,walkState = walkState, destination = WalkViewModel.instance.destination.value[1])
        Column {
            if(walkState==WalkStateType.PREPARE) {
                LazyRow(
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    item {
                        ButtonBoarder(
                            selectedId = selectedId,
                            setSelectedId = setSelectedId,
                            id = 0,
                            type = OptionType.SHORTEST,
                            name = "목적지 시설까지\n바로 도착",
                            totalDistance = WalkViewModel.instance.shortestPath.value!!.totalDistance,
                            totalTime = WalkViewModel.instance.shortestPath.value!!.totalTime
                        )
                    }
                    items(WalkViewModel.instance.paths.value.size) {
                        ButtonBoarder(
                            selectedId = selectedId,
                            setSelectedId = setSelectedId,
                            id = it + 1,
                            type = OptionType.WALK_TRAIL,
                            name = "${WalkViewModel.instance.paths.value[it].name}\n산책하면서 도착",
                            totalDistance = WalkViewModel.instance.paths.value[it].totalDistance,
                            totalTime = WalkViewModel.instance.paths.value[it].totalTime
                        )
                    }
                }
            }
            when(walkState){
                WalkStateType.PREPARE->{
                    Button(
                        buttonSize = ButtonSize.LARGE,
                        buttonText = "시작하기",
                        buttonOnClick = {
                            setWalkState(WalkStateType.WALK)
                        }
                    )
                }
                WalkStateType.WALK -> {
                    ButtonRounded(
                        buttonText = "산책 종료하기",
                        buttonOnClick = {
                            navController.navigate(Screens.MainScreen.name){
                                popUpTo(0)
                            }
                        }
                    )
                }
                WalkStateType.ARRIVE -> {

                }
                WalkStateType.MAP -> {

                }

            }
        }
    }

}
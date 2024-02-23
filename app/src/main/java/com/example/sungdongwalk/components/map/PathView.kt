package com.example.sungdongwalk.components.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.R
import com.example.sungdongwalk.activities.MainActivity
import com.example.sungdongwalk.components.WalkStateType
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDgreen
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.viewmodels.LocationViewModel
import com.example.sungdongwalk.viewmodels.WalkViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapType
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.PathOverlay
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun PathView(
    type: WalkStateType,
    setType: (WalkStateType) -> Unit,
    selectedId: Int,
){
    val shortestPath = WalkViewModel.instance.shortestPath.value!!.coordinates.map{ LatLng(it[1], it[0]) }
    val paths = WalkViewModel.instance.paths.value.map { it.coordinates.map { LatLng(it[1], it[0]) } }
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
    }
    val start = shortestPath[0]
    val dest = shortestPath[shortestPath.size-1]

    NaverMap(
        locationSource = MainActivity.locationSource,
        modifier = Modifier
            .fillMaxSize(),
        uiSettings = MapUiSettings(
            isCompassEnabled = true,
            isLocationButtonEnabled = type==WalkStateType.WALK,
            isScrollGesturesEnabled = true,
            isScaleBarEnabled = type==WalkStateType.WALK,
            isIndoorLevelPickerEnabled = true,
            isLogoClickEnabled = true,
            isTiltGesturesEnabled = false,
            isZoomControlEnabled = type==WalkStateType.WALK
        ),
        properties = MapProperties(
            mapType = MapType.Basic,
            isIndoorEnabled = true,
            locationTrackingMode = if(type==WalkStateType.WALK) LocationTrackingMode.Face else LocationTrackingMode.NoFollow
        ),
        cameraPositionState = cameraPositionState,
        onLocationChange = {
            val curr = LatLng(it.latitude, it.longitude)
            if (LocationViewModel.instance.isArrive(curr, dest)){
                setType(WalkStateType.ARRIVE)
            }
        }
    ){
        if(type==WalkStateType.PREPARE){
            PathOverlay(
                coords = shortestPath,
                width = 7.dp,
                color = if(selectedId==0) SDgreen else Gray500,
                outlineColor = SDwhite,
                globalZIndex = if(selectedId==0) 1 else 0,
            )
            Marker(
                icon = OverlayImage.fromResource(R.drawable.pin_default),
                state = MarkerState(position = shortestPath[shortestPath.size-1]),
                captionText = "목적지"
            )
            WalkViewModel.instance.paths.value.forEachIndexed { index, path->
                val coordinates = path.coordinates.map{ LatLng(it[1], it[0]) }
                PathOverlay(
                    coords = coordinates,
                    width = 7.dp,
                    color = if(selectedId==index+1) SDgreen else Gray500,
                    outlineColor = SDwhite,
                    globalZIndex = if(selectedId==index+1) 1 else 0
                )
                Marker(
                    icon = OverlayImage.fromResource(R.drawable.pin_default),
                    state = MarkerState(position = LatLng(
                        path.ycoordinate,
                        path.xcoordinate
                    )),
                    captionText = path.name,
                )
            }

            val coords = listOf(dest).plus(WalkViewModel.instance.paths.value.map { LatLng(it.ycoordinate, it.xcoordinate) })
            cameraPositionState.move(CameraUpdate.fitBounds(
                LatLngBounds(
                    start,
                    LocationViewModel.instance.getFarCoord(start, coords)
                ),
                150,300,150,600
            ).animate(CameraAnimation.Easing))
            
        } else {
            val selectedPath = when(selectedId){
                0 -> shortestPath
                else -> paths[selectedId-1]
            }
            PathOverlay(
                coords = selectedPath,
                width = 7.dp,
                color = SDblue,
                outlineColor = SDwhite,
                globalZIndex = 1
            )
            cameraPositionState.move(CameraUpdate.zoomTo(16.0))
            cameraPositionState.move(CameraUpdate.scrollTo(start))

        }
    }
}
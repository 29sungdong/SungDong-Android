package com.example.sungdongwalk.components.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sungdongwalk.R
import com.example.sungdongwalk.activities.MainActivity
import com.example.sungdongwalk.api.retrofit.RetrofitManager
import com.example.sungdongwalk.viewmodels.LocationViewModel
import com.example.sungdongwalk.viewmodels.PlaceViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
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
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage
@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapView(setIsLoading: (Boolean)-> Unit){
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
    }
    NaverMap(
        locationSource = MainActivity.locationSource,
        modifier = Modifier
            .fillMaxSize(),
        uiSettings = MapUiSettings(
            isCompassEnabled = true,
            isLocationButtonEnabled = true,
            isScrollGesturesEnabled = true,
            isScaleBarEnabled = true,
            isIndoorLevelPickerEnabled = true,
            isLogoClickEnabled = true,
            isZoomControlEnabled = false
        ),
        properties = MapProperties(
            mapType = MapType.Basic,
            isIndoorEnabled = true,
            locationTrackingMode = LocationTrackingMode.Follow
        ),
        cameraPositionState = cameraPositionState
    ){
        PlaceViewModel.instance.markers.value.forEach { place ->
            Marker(
                icon = OverlayImage.fromResource(if(place.hasEvent) R.drawable.pin_event else R.drawable.pin_default),
                state = MarkerState(position = LatLng(
                    place.ycoordinate.toDouble(),
                    place.xcoordinate.toDouble()
                )),
                captionText = place.name,
            ){
                setIsLoading(true)
                RetrofitManager.instance.getShortestPath(
                    placeId = place.id,
                    xCoordinate = LocationViewModel.instance.location.value.longitude.toString(),
                    yCoordinate = LocationViewModel.instance.location.value.latitude.toString(),
                )
                RetrofitManager.instance.getPaths(
                    placeId = place.id,
                    placeName = place.name,
                    placeImg = "",//place.image,
                    xCoordinate = LocationViewModel.instance.location.value.longitude.toString(),
                    yCoordinate = LocationViewModel.instance.location.value.latitude.toString(),
                )
                false
            }
        }
        val start = LocationViewModel.instance.location.value
        val farMarker = LocationViewModel.instance.getFarCoord(
            start,
            PlaceViewModel.instance.markers.value.map { place -> LatLng(
                place.ycoordinate.toDouble(),
                place.xcoordinate.toDouble()
            )}
        )
        cameraPositionState.move(CameraUpdate.fitBounds(LatLngBounds(start, farMarker), 200))
    }

}

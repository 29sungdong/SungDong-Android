package com.example.sungdongwalk.components.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sungdongwalk.R
import com.example.sungdongwalk.activities.MainActivity
import com.example.sungdongwalk.viewmodels.PlaceViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapType
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.overlay.OverlayImage

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapView(){
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
            isLogoClickEnabled = true
        ),
        properties = MapProperties(
            mapType = MapType.Basic,
            isIndoorEnabled = true,
            locationTrackingMode = LocationTrackingMode.Follow,
        ),
    ){
        PlaceViewModel.instance.markers.value.forEach { place ->
            Marker(
                icon = OverlayImage.fromResource(if(place.hasEvent) R.drawable.pin_event else R.drawable.pin_default),
                state = MarkerState(position = LatLng(
                    place.ycoordinate.toDouble(),
                    place.xcoordinate.toDouble()
                )),
                captionText = place.name,
            )
        }
    }

}

package com.example.sungdongwalk.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sungdongwalk.activities.Glob
import com.naver.maps.geometry.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.abs

class LocationViewModel: ViewModel(){

    companion object{
        val instance = LocationViewModel()
    }

    private val _location = MutableStateFlow(Glob.prefs.getLastLocation())
    val location : StateFlow<LatLng> = _location
    fun updateLocation(longitude: Double, latitude: Double){
        viewModelScope.launch {
            _location.emit(LatLng(latitude, longitude))
        }
    }

    fun isArrive(curr: LatLng, dest: LatLng): Boolean{
        return getDistance(curr, dest) < 0.001
    }
    fun getFarCoord(start: LatLng, coords: List<LatLng>): LatLng{
        var farCoord = start
        var maxDist = 0.0
        coords.forEach {
            val currDist = getDistance(start, it)
            if(currDist > maxDist) {
                maxDist = currDist
                farCoord = it
            }
        }
        return farCoord
    }
    private fun getDistance(a: LatLng, b: LatLng): Double{
        return abs(a.latitude-b.latitude) + abs(a.longitude-b.longitude)
    }
}
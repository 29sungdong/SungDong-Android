package com.example.sungdongwalk.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sungdongwalk.location.LocationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel(){

    companion object{
        val instance = LocationViewModel()
    }

    private val _location = MutableStateFlow(Pair("",""))
    val location : StateFlow<Pair<String, String>> = _location
    fun updateLocation(longitude: Double, latitude: Double){
        viewModelScope.launch {
            _location.emit(Pair(longitude.toString(), latitude.toString()))
        }
    }
}
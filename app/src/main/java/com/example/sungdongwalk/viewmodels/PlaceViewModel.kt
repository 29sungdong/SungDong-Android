package com.example.sungdongwalk.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sungdongwalk.api.Dto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlaceViewModel: ViewModel(){

    companion object{
        val instance = PlaceViewModel()
    }

    private val _places = MutableStateFlow<List<Dto.SimplePlaceVo>>(listOf())
    val places : StateFlow<List<Dto.SimplePlaceVo>> = _places

    fun updatePlaces(newPlaces: List<Dto.SimplePlaceVo>){
        viewModelScope.launch {
            _places.emit(newPlaces)
        }
    }

    private val _markers = MutableStateFlow<List<Dto.MarkerVo>>(listOf())
    val markers : StateFlow<List<Dto.MarkerVo>> = _markers

    fun updateMarkers(newMarkers: List<Dto.MarkerVo>){
        viewModelScope.launch {
            _markers.emit(newMarkers)
        }
    }
}
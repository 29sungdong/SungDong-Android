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
    // 가까운 시설 데이터
    private val _places = MutableStateFlow<List<Dto.SimplePlaceVo>>(listOf())
    val places : StateFlow<List<Dto.SimplePlaceVo>> = _places

    fun updatePlaces(newPlaces: List<Dto.SimplePlaceVo>){
        viewModelScope.launch {
            _places.emit(newPlaces)
        }
    }
    // 시설 마커 데이터
    private val _markers = MutableStateFlow<List<Dto.MarkerVo>>(listOf())
    val markers : StateFlow<List<Dto.MarkerVo>> = _markers

    fun updateMarkers(newMarkers: List<Dto.MarkerVo>){
        viewModelScope.launch {
            _markers.emit(newMarkers)
        }
    }
    // 방문한 시설 데이터
    private val _userPlace = MutableStateFlow<List<Dto.PlaceResponseDTO>>(listOf())
    val userPlace : StateFlow<List<Dto.PlaceResponseDTO>> = _userPlace

    // 시설 방문율 데이터
    private val _userPlaceAchievementRate = MutableStateFlow(0)
    val userPlaceAchievementRate : StateFlow<Int> = _userPlaceAchievementRate

    fun updateUserPlace(newUserPlaces: List<Dto.PlaceResponseDTO>){
        val rate = newUserPlaces.count { it.isVisited } / newUserPlaces.size.toDouble() * 100
        viewModelScope.launch {
            _userPlace.emit(newUserPlaces)
            _userPlaceAchievementRate.emit(rate.toInt())
        }
    }
}
package com.example.sungdongwalk.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sungdongwalk.api.Dto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WalkViewModel: ViewModel() {
    companion object{
        val instance = WalkViewModel()
    }
    // 산책 목적지 데이터
    private val _destination = MutableStateFlow<List<String>>(listOf())
    val destination : StateFlow<List<String>> = _destination

    fun updateDestination(placeId: String, placeName: String, placeImg: String){
        viewModelScope.launch {
            _destination.emit(listOf(placeId, placeName, placeImg))
        }
    }
    fun initDestination(){
        viewModelScope.launch {
            _destination.emit(listOf())
        }
    }
    // 산책 경로 데이터
    private val _shortestPath = MutableStateFlow<Dto.ShortestPathResponseDTO?>(null)
    val shortestPath : StateFlow<Dto.ShortestPathResponseDTO?> = _shortestPath

    fun updateShortestPath(newPath: Dto.ShortestPathResponseDTO){
        viewModelScope.launch {
            _shortestPath.emit(newPath)
        }
    }

    private val _paths = MutableStateFlow<List<Dto.WalkPathResponseDTO>>(listOf())
    val paths : StateFlow<List<Dto.WalkPathResponseDTO>> = _paths

    fun updatePath(newPath: List<Dto.WalkPathResponseDTO>){
        viewModelScope.launch {
            _paths.emit(newPath)
        }
    }
    // 뱃지 데이터

}
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

    private val _destination = MutableStateFlow<List<String>>(listOf())
    val destination : StateFlow<List<String>> = _destination

    fun updateDestination(placeId: String, placeName: String, placeImg: String){
        viewModelScope.launch {
            _destination.emit(listOf(placeId, placeName, placeImg))
        }
    }

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
}
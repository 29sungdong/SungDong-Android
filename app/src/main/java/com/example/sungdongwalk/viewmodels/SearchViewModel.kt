package com.example.sungdongwalk.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sungdongwalk.api.Dto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

enum class SearchActionType {
   ENTER ,DELETE
}

class SearchViewModel: ViewModel() {

    companion object{
        val instance = SearchViewModel()
    }

    private val _searchKeyword = MutableStateFlow("")
    val searchKeyword : StateFlow<String> = _searchKeyword

    fun updateSearchKeyword(keyword: String){
        viewModelScope.launch {
            _searchKeyword.emit(keyword)
        }
    }

    private val _searchResults = MutableStateFlow<List<Dto.MarkerVo>>(listOf())
    val searchResults: StateFlow<List<Dto.MarkerVo>> = _searchResults

    fun updateSearchResults(actionType: SearchActionType, results: List<Dto.MarkerVo>?){
        viewModelScope.launch {
            when(actionType){
                SearchActionType.ENTER -> _searchResults.emit(results!!)
                SearchActionType.DELETE -> _searchResults.emit(listOf())
            }
        }
    }
}
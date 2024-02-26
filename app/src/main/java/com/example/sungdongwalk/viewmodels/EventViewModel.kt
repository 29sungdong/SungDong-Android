package com.example.sungdongwalk.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventViewModel: ViewModel() {
    companion object{
        val instance = EventViewModel()
    }
    private val _url = MutableStateFlow("")
    val url : StateFlow<String> = _url

    fun updateUrl(newUrl: String){
        viewModelScope.launch {
            _url.emit(newUrl)
        }
    }

}
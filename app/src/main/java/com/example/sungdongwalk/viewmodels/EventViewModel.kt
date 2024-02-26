package com.example.sungdongwalk.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sungdongwalk.api.Dto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventViewModel: ViewModel() {
    companion object{
        val instance = EventViewModel()
    }
    private val _url = MutableStateFlow("https://yeyak.seoul.go.kr/web/reservation/selectReservView.do?rsv_svc_id=S221217154020297822"
    )
    val url : StateFlow<String> = _url

    fun updateUrl(newUrl: String){
        viewModelScope.launch {
            _url.emit(newUrl)
        }
    }
    private val _events = MutableStateFlow<List<List<Dto.SimpleEventVo>>>((1..31).map{ listOf() })
    val events : StateFlow<List<List<Dto.SimpleEventVo>>> = _events

    fun updateEvents(newEvents: List<List<Dto.SimpleEventVo>>){
        viewModelScope.launch {
            val events = events.value.toMutableList()
            events.forEachIndexed { index, simpleEventVos ->
                events[index] = newEvents[index]
            }
            _events.emit(events)
        }
    }

}
package com.example.sungdongwalk.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.api.retrofit.RetrofitManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    companion object{
        val instance = LoginViewModel()
    }

    private val _id = MutableStateFlow(0)
    val id : StateFlow<Int> = _id

    private val _token = MutableStateFlow("")
    val token : StateFlow<String> = _token

    fun updateUserLogin(tokenResponseDTO: Dto.TokenResponseDTO){
        val (updatedId, updatedToken) = tokenResponseDTO
        viewModelScope.launch {
            _id.emit(updatedId)
            _token.emit(updatedToken)
        }
    }
    init {
        RetrofitManager.instance.postUserLogin("string1","string")
    }
}
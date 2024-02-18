package com.example.sungdongwalk.api.retrofit

import com.example.sungdongwalk.api.utils.API
import com.example.sungdongwalk.api.utils.API.BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.create
import java.lang.Exception

class RetrofitManager {

    companion object{
        val instance = RetrofitManager()
    }

    // 레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(BASE_URL)?.create(IRetrofit::class.java)

    fun getPlacesSearch(
        keyword: String?,
        setResults: (List<String>) -> Unit
    ) = CoroutineScope(IO).launch{
        val term = keyword ?: ""
        val results : List<String>?

        try{
            val request = CoroutineScope(IO).async { iRetrofit?.getPlacesSearch(term) }
            val response = request.await()
            when(response?.code()){
                200 -> {
                    results = response.body()?.markers?.map { it.name } ?: listOf()
                    setResults(results)
                }
            }
        }catch(e: Exception){

        }
    }
}
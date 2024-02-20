package com.example.sungdongwalk.api.retrofit


import android.util.Log
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.api.utils.API.BASE_URL
import com.example.sungdongwalk.viewmodels.PlaceViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RetrofitManager {

    companion object{
        val instance = RetrofitManager()
        val TAG = "Exception"
    }

    // 레트로핏 인터페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(BASE_URL)?.create(IRetrofit::class.java)

    fun getPlaces(
        xCoordinate: String,
        yCoordinate: String
    ) = CoroutineScope(IO).launch {
        try{
            val request = CoroutineScope(IO).async { iRetrofit?.getPlaces(xCoordinate, yCoordinate) }
            val response = request.await()

            when(response?.code()){
                200 -> {
                    val places = response.body()?.places
                    if (places != null) {
                        PlaceViewModel.instance.updatePlaces(places)
                    }
                }
                else -> {}
            }
        }catch(e: Exception){
            Log.d(TAG, e.toString())
        }
    }
    fun getPlacesSearch(
        keyword: String?,
        setResults: (List<Dto.MarkerVo>)->Unit
    ) = CoroutineScope(IO).launch{
        val term = keyword ?: ""
        if (term == ""){
            setResults(listOf())
            return@launch
        }
        try{
            val request = CoroutineScope(IO).async { iRetrofit?.getPlacesSearch(term) }
            val response = request.await()

            when(response?.code()){
                200 -> {
                    val results = response.body()?.markers ?: listOf()
                    setResults(results)
                }
                else -> {
                }
            }
        }catch(e: Exception){
            Log.d(TAG, e.toString())
        }
    }
    fun getPlacesMarker(
        xCoordinate: String,
        yCoordinate: String,
        limit: Int
    ) = CoroutineScope(IO).launch {
        try{
            val request = CoroutineScope(IO).async { iRetrofit?.getPlacesMarker(xCoordinate,yCoordinate,limit) }
            val response = request.await()
            when(response?.code()){
                200 -> {
                    val markers = response.body()?.markers ?: listOf()
                    PlaceViewModel.instance.updateMarkers(markers)
                }
                else -> {}
            }
        }catch (e:Exception){
            Log.d(TAG, e.toString())
        }
    }
}
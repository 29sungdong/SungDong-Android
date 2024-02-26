package com.example.sungdongwalk.api.retrofit


import android.util.Log
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.api.utils.API.BASE_URL
import com.example.sungdongwalk.viewmodels.EventViewModel
import com.example.sungdongwalk.viewmodels.PlaceViewModel
import com.example.sungdongwalk.viewmodels.WalkViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RetrofitManager {

    companion object{
        val instance = RetrofitManager()
        val TAG = "Request_Error"
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
                else -> {

                }
            }
        }catch (e:Exception){
            Log.d(TAG, e.toString())
        }
    }
    fun getShortestPath(
        placeId: Int,
        xCoordinate: String,
        yCoordinate: String,
    ) = CoroutineScope(IO).launch{
        try{
            val request = CoroutineScope(IO).async { iRetrofit?.getShortestWalkPath(placeId, xCoordinate,yCoordinate) }
            val response = request.await()
            when(response?.code()){
                200 -> {
                    val paths = response.body()
                    if (paths != null) {
                        WalkViewModel.instance.updateShortestPath(paths)
                    }
                }
                else -> {
                    Log.d(TAG, response?.code().toString())
                }
            }
        } catch (e: Exception){
            Log.d(TAG, e.toString())
        }
    }
    // MapView, NavigatorTopSearch
    fun getPaths(
        placeId: Int,
        placeName: String,
        placeImg: String,
        xCoordinate: String,
        yCoordinate: String,
    ) = CoroutineScope(IO).launch{
        try{
            val request = CoroutineScope(IO).async { iRetrofit?.getWalkPath(placeId, xCoordinate,yCoordinate) }
            val response = request.await()
            when(response?.code()){
                200 -> {
                    val paths = response.body()?.paths ?: listOf()
                    WalkViewModel.instance.updatePath(paths)
                    WalkViewModel.instance.updateDestination(placeId.toString(), placeName, placeImg)
                }
                else -> {
                    Log.d(TAG, response?.code().toString())
                }
            }
        } catch (e: Exception){
            Log.d(TAG, e.toString())
        }
    }
    fun getEvents(
        category: String?,
        placeId: Int?,
        setEvents: (List<Dto.SimpleEventVo>) -> Unit
    ) = CoroutineScope(IO).launch {
        try{
            val request = CoroutineScope(IO).async { iRetrofit?.getEvents(category,placeId) }
            val response = request.await()
            when(response?.code()){
                200 -> {
                    val events = response.body()?.events ?: listOf()
                    setEvents(events)
                }
                else ->{
                    Log.d(TAG, "Error Code: ${response?.code()}")
                }
            }
        }catch (e: Exception){
            Log.d(TAG, e.toString())
        }
    }
    fun getEventCalender(
        startDate: String,
        endDate: String,
    ) = CoroutineScope(IO).launch {
        try{
            val request = CoroutineScope(IO).async { iRetrofit?.getEventsCalender(startDate, endDate) }
            val response = request.await()
            when(response?.code()){
                200 -> {
                    val newEvents = response.body()?.data?.map{it.events} ?: listOf()
                    EventViewModel.instance.updateEvents(newEvents)
                }
                else -> {

                }
            }
        }catch (e: Exception){
            Log.d(TAG, e.toString())
        }
    }
}
package com.example.sungdongwalk.api.utils

import android.content.Context
import android.content.SharedPreferences
import com.naver.maps.geometry.LatLng

class PreferenceManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    fun getLastLocation(): LatLng{
        LatLng(37.563610742289,127.03661264605)
        val latitude=prefs.getString("latitude", "37.563610742289")
        val longitude=prefs.getString("longitude", "127.03661264605")
        return LatLng(latitude!!.toDouble(), longitude!!.toDouble())
    }

    fun saveCurrentLocation(latitude: String, longitude: String){
        prefs.edit().putString("latitude", latitude).apply()
        prefs.edit().putString("longitude", longitude).apply()
    }
}
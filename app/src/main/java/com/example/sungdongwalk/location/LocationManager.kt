package com.example.sungdongwalk.location

import android.annotation.SuppressLint
import com.example.sungdongwalk.activities.Glob
import com.example.sungdongwalk.viewmodels.LocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient

class LocationManager(private val locationClient: FusedLocationProviderClient) {

    @SuppressLint("MissingPermission")
    fun getLastLocation(){
        locationClient.lastLocation
            .addOnSuccessListener {location ->
                if(location != null){
                    LocationViewModel.instance.updateLocation(location.longitude, location.latitude)
                    Glob.prefs.saveCurrentLocation(location.latitude.toString(), location.longitude.toString())
                }
            }
    }
}
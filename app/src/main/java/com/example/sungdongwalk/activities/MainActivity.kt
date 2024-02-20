package com.example.sungdongwalk.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sungdongwalk.api.retrofit.RetrofitManager
import com.example.sungdongwalk.components.navigator_bottom.AppNavigation
import com.example.sungdongwalk.location.LocationManager
import com.example.sungdongwalk.ui.theme.SungdongWalkTheme
import com.example.sungdongwalk.viewmodels.LocationViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.map.util.FusedLocationSource
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    companion object{
        lateinit var locationSource: FusedLocationSource
    }
    private val LOCATION_REQUEST_CODE = 10001
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationManager: LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationSource = FusedLocationSource(this, LOCATION_REQUEST_CODE)
        locationManager = LocationManager(fusedLocationProviderClient)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    LocationViewModel.instance.location.collect{
                        RetrofitManager.instance.getPlaces(
                            it.first,
                            it.second
                        )
                        RetrofitManager.instance.getPlacesMarker(
                            it.first,
                            it.second,
                            10
                        )
                    }
                }
            }
        }
        setContent {
            SungdongWalkTheme {
                AppNavigation()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        askPermission()
    }
    private fun askPermission(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                LOCATION_REQUEST_CODE
            )
        }
        else{
            locationManager.getLastLocation()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == LOCATION_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Permission granted
                locationManager.getLastLocation()
            }
            else{
                // Permission not granted
                finishAffinity()
            }
        }
    }
}
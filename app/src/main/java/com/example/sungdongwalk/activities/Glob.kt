package com.example.sungdongwalk.activities

import android.app.Application
import com.example.sungdongwalk.api.utils.PreferenceManager

class Glob: Application() {
    companion object {
        lateinit var prefs: PreferenceManager
    }
    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceManager(applicationContext)
    }
}
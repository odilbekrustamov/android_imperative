package com.exsample.android_imperative

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImperativeApp: Application() {
    private val TAG = "ImperativeApp"

    override fun onCreate() {
        super.onCreate()
    }

}
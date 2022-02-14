package com.shong.hilt_mvvm_simple

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppBase : Application() {
    private val TAG = this::class.java.simpleName+"_sHong"
}
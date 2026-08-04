package com.modify.music

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ModifyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}

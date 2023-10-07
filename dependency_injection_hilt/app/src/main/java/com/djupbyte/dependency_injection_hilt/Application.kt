package com.djupbyte.dependency_injection_hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    // Application-specific code
}
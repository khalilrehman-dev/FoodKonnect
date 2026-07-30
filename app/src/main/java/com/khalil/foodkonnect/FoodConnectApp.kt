package com.khalil.foodkonnect

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.osmdroid.config.Configuration

@HiltAndroidApp
class FoodKonnectApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // osmdroid requires a unique user agent identifying the app,
        // per OpenStreetMap's tile usage policy — without this, tile
        // requests may be blocked or rate-limited.
        Configuration.getInstance().userAgentValue = packageName
    }
}
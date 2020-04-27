package com.adamg.livetrack.platform.location

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.adamg.livetrack.applicationinterfaces.service.LocationService
import javax.inject.Inject

internal class AndroidLocationService @Inject constructor(
    private val context: Context
) : LocationService {

    override fun start() {
        ContextCompat.startForegroundService(context, Intent(context, LocationForegroundService::class.java))
    }

    override fun stop() {
        context.stopService(Intent(context, LocationForegroundService::class.java))
    }
}

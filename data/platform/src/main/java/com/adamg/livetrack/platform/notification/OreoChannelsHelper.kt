package com.adamg.livetrack.platform.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.adamg.livetrack.platform.R
import javax.inject.Inject

internal class OreoChannelsHelper @Inject constructor(
    private val notificationManager: NotificationManager,
    private val context: Context
) {

    companion object {

        const val FOREGROUND_TRACKING_CHANNEL = "Foreground tracking"
    }

    fun createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val foregroundTrackingChannel = NotificationChannel(
                FOREGROUND_TRACKING_CHANNEL,
                context.getString(R.string.foreground_tracking_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setShowBadge(false)
                enableLights(true)
                enableVibration(true)
            }

            notificationManager.createNotificationChannels(
                listOf(foregroundTrackingChannel)
            )
        }
    }
}

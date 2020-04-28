package com.adamg.livetrack.platform.notification

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import javax.inject.Inject

internal class AndroidNotificationManager @Inject internal constructor(
    oreoChannelsHelper: OreoChannelsHelper,
    private val context: Context
) {

    init {
        oreoChannelsHelper.createChannels()
    }

    fun getForegroundTrackingNotification(): Notification {
        return NotificationCompat.Builder(context, OreoChannelsHelper.FOREGROUND_TRACKING_CHANNEL)
            .setOngoing(true)
            .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
            .setWhen(System.currentTimeMillis())
            .build()
    }

    companion object {
        const val FOREGROUND_TRACKING_NOTIFICATION_ID = 232
    }
}

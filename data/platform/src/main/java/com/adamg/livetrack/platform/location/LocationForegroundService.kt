package com.adamg.livetrack.platform.location

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.adamg.livetrack.applicationimplementations.usecase.ObserveLocationChanges
import com.adamg.livetrack.platform.notification.AndroidNotificationManager
import dagger.android.DaggerService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
internal class LocationForegroundService : DaggerService(), CoroutineScope {

    @Inject
    lateinit var observeLocationChanges: ObserveLocationChanges

    @Inject
    lateinit var notificationManager: AndroidNotificationManager

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(
            AndroidNotificationManager.FOREGROUND_TRACKING_NOTIFICATION_ID,
            notificationManager.getForegroundTrackingNotification()
        )

        observeLocationChanges.execute()
            .launchIn(this)

        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}

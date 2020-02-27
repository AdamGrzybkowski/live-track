package com.adamg.livetrack

import com.adamg.livetrack.di.DaggerApplicationComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.DaggerApplication

class LiveTrackApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

    override fun applicationInjector() = DaggerApplicationComponent.factory().create(this)
}

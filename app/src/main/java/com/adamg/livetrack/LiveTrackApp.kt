package com.adamg.livetrack

import android.app.Application
import com.adamg.livetrack.di.DaggerApplicationComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class LiveTrackApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        DaggerApplicationComponent.factory()
            .create(this)
            .inject(this)
    }

    override fun androidInjector() = dispatchingAndroidInjector
}

package com.adamg.livetrack.platform.location

import com.adamg.livetrack.applicationinterfaces.service.LocationProvider
import com.adamg.livetrack.applicationinterfaces.service.LocationService
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class LocationModule {

    @ContributesAndroidInjector
    internal abstract fun bindLocationForegroundService(): LocationForegroundService

    @Binds
    @Reusable
    internal abstract fun bindLocationProvider(impl: AndroidLocationProvider): LocationProvider

    @Binds
    @Reusable
    internal abstract fun bindLocationService(impl: AndroidLocationService): LocationService
}

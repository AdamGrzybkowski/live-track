package com.adamg.livetrack.presentation.ui

import com.adamg.livetrack.presentation.ui.tracking.TrackingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityFragmentModule {

    @ContributesAndroidInjector
    abstract fun bindTrackingFragment(): TrackingFragment
}

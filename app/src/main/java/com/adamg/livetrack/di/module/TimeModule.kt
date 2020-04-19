package com.adamg.livetrack.di.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import org.threeten.bp.Clock

@Module
class TimeModule {

    @Provides
    @Reusable
    fun provideClock(): Clock = Clock.systemDefaultZone()
}

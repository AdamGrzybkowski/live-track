package com.adamg.livetrack.applicationimplementations

import com.adamg.livetrack.applicationimplementations.repository.DefaultTrackRepository
import com.adamg.livetrack.applicationimplementations.repository.TrackRepository
import com.adamg.livetrack.applicationinterfaces.CoroutinesContextFacade
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class DomainModule {

    @Binds
    @Reusable
    internal abstract fun bindsTrackRepository(impl: DefaultTrackRepository): TrackRepository

    @Binds
    @Reusable
    internal abstract fun bindsContextFacade(impl: DefaultContextFacade): CoroutinesContextFacade
}

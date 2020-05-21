package com.adamg.livetrack.applicationimplementations

import com.adamg.livetrack.applicationinterfaces.CoroutinesContextFacade
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
abstract class DomainModule {

    @Binds
    @Reusable
    internal abstract fun bindsContextFacade(impl: DefaultContextFacade): CoroutinesContextFacade
}

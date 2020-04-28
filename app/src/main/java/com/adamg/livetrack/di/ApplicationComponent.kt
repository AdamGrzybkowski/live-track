package com.adamg.livetrack.di

import android.content.Context
import com.adamg.livetrack.LiveTrackApp
import com.adamg.livetrack.applicationimplementations.DomainModule
import com.adamg.livetrack.di.module.DbModule
import com.adamg.livetrack.di.module.TimeModule
import com.adamg.livetrack.platform.di.PlatformModule
import com.adamg.livetrack.presentation.ui.di.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        DomainModule::class,
        DbModule::class,
        TimeModule::class,
        PlatformModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<LiveTrackApp> {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context
        ): ApplicationComponent
    }
}

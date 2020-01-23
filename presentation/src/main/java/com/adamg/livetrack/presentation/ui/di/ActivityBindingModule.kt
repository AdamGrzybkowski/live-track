package com.adamg.livetrack.presentation.ui.di

import com.adamg.livetrack.presentation.ui.MainActivity
import com.adamg.livetrack.presentation.ui.MainActivityFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainActivityFragmentModule::class])
    abstract fun bindMainActivity(): MainActivity
}

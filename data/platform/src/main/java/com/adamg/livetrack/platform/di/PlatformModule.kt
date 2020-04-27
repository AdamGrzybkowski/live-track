package com.adamg.livetrack.platform.di

import com.adamg.livetrack.platform.location.LocationModule
import com.adamg.livetrack.platform.notification.NotificationModule
import dagger.Module

@Module(includes = [LocationModule::class, NotificationModule::class])
abstract class PlatformModule

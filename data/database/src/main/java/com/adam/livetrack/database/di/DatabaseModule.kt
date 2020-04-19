package com.adam.livetrack.database.di

import com.adam.livetrack.database.LiveTrackDb
import com.adam.livetrack.database.localsource.DbTrackLocalSource
import com.adamg.livetrack.applicationinterfaces.localsource.TrackLocalSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
abstract class DatabaseModule {

    @Module
    companion object {

        @Provides
        @Singleton
        fun provideDatabase(sqlDriver: SqlDriver) = LiveTrackDb(driver = sqlDriver)

        @Provides
        @Singleton
        fun provideTrackQueries(database: LiveTrackDb) = database.trackQueries

        @Provides
        @Singleton
        fun providePointQueries(database: LiveTrackDb) = database.pointQueries
    }

    @Binds
    @Reusable
    internal abstract fun bindTrackLocalSource(impl: DbTrackLocalSource): TrackLocalSource
}

package com.adamg.livetrack.di.module

import android.content.Context
import com.adam.livetrack.database.LiveTrackDb
import com.adam.livetrack.database.di.DatabaseModule
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
class DbModule {

    @Provides
    @Singleton
    fun provideAndroidSqlDriver(context: Context): SqlDriver {
        return AndroidSqliteDriver(LiveTrackDb.Schema, context, "livetrack.db")
    }
}

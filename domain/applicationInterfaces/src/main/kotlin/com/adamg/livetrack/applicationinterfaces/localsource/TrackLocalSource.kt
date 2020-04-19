package com.adamg.livetrack.applicationinterfaces.localsource

import com.adamg.livetrack.business.entities.Track
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.ZonedDateTime

interface TrackLocalSource {

    fun getLiveTrack(): Flow<Track?>

    suspend fun createTrack(startedAt: ZonedDateTime)

    suspend fun finishTrack(trackId: Long, finishedAt: ZonedDateTime)

    suspend fun addTrackPoint(
        trackId: Long,
        latitude: Float,
        longitude: Float,
        registeredAt: ZonedDateTime
    )
}

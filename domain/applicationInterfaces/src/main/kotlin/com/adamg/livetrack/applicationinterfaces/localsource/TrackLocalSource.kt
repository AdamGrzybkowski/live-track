package com.adamg.livetrack.applicationinterfaces.localsource

import com.adamg.livetrack.business.entities.Track
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.Instant

interface TrackLocalSource {

    fun getLiveTrack(): Flow<Track?>

    suspend fun createTrack(startedAt: Instant)

    suspend fun finishTrack(trackId: Long, finishedAt: Instant)

    suspend fun addTrackPoint(
        trackId: Long,
        latitude: Double,
        longitude: Double,
        registeredAt: Instant
    )
}

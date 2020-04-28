package com.adamg.livetrack.applicationimplementations.repository

import com.adamg.livetrack.applicationinterfaces.localsource.TrackLocalSource
import com.adamg.livetrack.business.entities.Track
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.Instant
import javax.inject.Inject

internal class DefaultTrackRepository @Inject constructor(
    private val trackLocalSource: TrackLocalSource
) : TrackRepository {

    override fun getLiveTrack(): Flow<Track?> {
        return trackLocalSource.getLiveTrack()
    }

    override suspend fun createTrack(startedAt: Instant) {
        trackLocalSource.createTrack(startedAt)
    }

    override suspend fun finishTrack(trackId: Long, finishedAt: Instant) {
        trackLocalSource.finishTrack(trackId, finishedAt)
    }

    override suspend fun addTrackPoint(
        trackId: Long,
        latitude: Double,
        longitude: Double,
        registeredAt: Instant
    ) {
        trackLocalSource.addTrackPoint(
            trackId = trackId,
            longitude = longitude,
            latitude = latitude,
            registeredAt = registeredAt
        )
    }
}

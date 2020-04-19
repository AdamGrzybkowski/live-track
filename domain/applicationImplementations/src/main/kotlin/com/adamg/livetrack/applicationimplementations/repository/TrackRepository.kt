package com.adamg.livetrack.applicationimplementations.repository

import com.adamg.livetrack.business.entities.Track
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.ZonedDateTime

interface TrackRepository {

    fun getLiveTrack(): Flow<Track?>

    suspend fun createTrack(startedAt: ZonedDateTime)

    suspend fun finishTrack(trackId: Long, finishedAt: ZonedDateTime)
}

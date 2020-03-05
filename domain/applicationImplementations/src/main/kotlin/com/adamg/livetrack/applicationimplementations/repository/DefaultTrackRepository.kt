package com.adamg.livetrack.applicationimplementations.repository

import com.adamg.livetrack.business.entities.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

internal class DefaultTrackRepository @Inject constructor() : TrackRepository {

    override fun getTracks(): Flow<List<Track>> {
        return flowOf(emptyList())
    }
}

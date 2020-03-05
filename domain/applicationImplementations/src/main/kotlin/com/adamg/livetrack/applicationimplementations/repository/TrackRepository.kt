package com.adamg.livetrack.applicationimplementations.repository

import com.adamg.livetrack.business.entities.Track
import kotlinx.coroutines.flow.Flow

interface TrackRepository {

    fun getTracks(): Flow<List<Track>>
}

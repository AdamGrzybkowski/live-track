package com.adam.livetrack.database.localsource

import com.adam.livetrack.database.Point
import com.adam.livetrack.database.PointQueries
import com.adam.livetrack.database.TrackQueries
import com.adamg.livetrack.applicationinterfaces.CoroutinesContextFacade
import com.adamg.livetrack.applicationinterfaces.localsource.TrackLocalSource
import com.adamg.livetrack.business.entities.Track
import com.adamg.livetrack.business.entities.TrackPoint
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.threeten.bp.Instant
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
internal class DbTrackLocalSource @Inject constructor(
    private val trackQueries: TrackQueries,
    private val pointQueries: PointQueries,
    private val contextFacade: CoroutinesContextFacade
) : TrackLocalSource {

    override fun getLiveTrack(): Flow<Track?> {
        val trackFlow = trackQueries.selectUnfinished().asFlow()
            .mapToOneOrNull()
            .distinctUntilChanged()
            .flowOn(contextFacade.io)
        val pointsFlow = pointQueries.selectAll().asFlow()
            .mapToList()
            .distinctUntilChanged()
            .flowOn(contextFacade.io)
        return trackFlow.combine(pointsFlow) { track, points ->
            track?.let {
                Track(
                    id = track.id,
                    startedAt = Instant.ofEpochMilli(track.started_at),
                    finishedAt = track.finished_at?.let { finishedAt -> Instant.ofEpochMilli(finishedAt) },
                    points = points.filter { it.track_id == track.id }.map { it.toDomainPoint() }
                )
            }
        }
            .distinctUntilChanged()
    }

    override suspend fun createTrack(startedAt: Instant) = withContext(contextFacade.io) {
        trackQueries.insert(started_at = startedAt.toEpochMilli())
    }

    override suspend fun finishTrack(trackId: Long, finishedAt: Instant) = withContext(contextFacade.io) {
        trackQueries.updateFinishedAt(finishedAt.toEpochMilli(), trackId)
    }

    override suspend fun addTrackPoint(
        trackId: Long,
        latitude: Double,
        longitude: Double,
        registeredAt: Instant
    ) = withContext(contextFacade.io) {
        pointQueries.insert(
            track_id = trackId,
            latitude = latitude.toString(),
            longitude = longitude.toString(),
            registered_at = registeredAt.toEpochMilli()
        )
    }

    private fun Point.toDomainPoint(): TrackPoint {
        return TrackPoint(
            id = id,
            latitude = latitude.toDouble(),
            longitude = longitude.toDouble(),
            registeredAt = Instant.ofEpochMilli(registered_at)
        )
    }
}

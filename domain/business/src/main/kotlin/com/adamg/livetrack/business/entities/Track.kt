package com.adamg.livetrack.business.entities

import org.threeten.bp.Instant

data class Track(
    val id: Long,
    val startedAt: Instant,
    val finishedAt: Instant?,
    val points: List<TrackPoint>
)

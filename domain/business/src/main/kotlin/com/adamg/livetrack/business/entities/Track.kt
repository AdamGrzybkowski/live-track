package com.adamg.livetrack.business.entities

import org.threeten.bp.ZonedDateTime

data class Track(
    val id: Long,
    val startedAt: ZonedDateTime,
    val finishedAt: ZonedDateTime?,
    val points: List<TrackPoint>
)

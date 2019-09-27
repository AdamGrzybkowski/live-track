package com.adamg.livetrack.business.entities

import org.threeten.bp.LocalDateTime

data class Track(
    val id: String,
    val startedAt: LocalDateTime,
    val finishedAt: LocalDateTime?,
    val points: List<TrackPoint>
)

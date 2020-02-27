package com.adamg.livetrack.business.entities

import org.threeten.bp.LocalDateTime

data class TrackPoint(
    val id: String,
    val latitude: Float,
    val longitude: Float,
    val time: LocalDateTime
)

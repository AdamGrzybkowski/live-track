package com.adamg.livetrack.business.entities

import java.time.LocalDateTime

data class TrackPoint(
    val id: String,
    val latitude: Float,
    val longitude: Float,
    val time: LocalDateTime
)

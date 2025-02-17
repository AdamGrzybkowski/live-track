package com.adamg.livetrack.business.entities

import org.threeten.bp.Instant

data class TrackPoint(
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val registeredAt: Instant
)

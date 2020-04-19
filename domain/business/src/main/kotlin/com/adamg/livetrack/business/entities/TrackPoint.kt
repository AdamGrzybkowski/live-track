package com.adamg.livetrack.business.entities

import org.threeten.bp.ZonedDateTime

data class TrackPoint(
    val id: Long,
    val latitude: Float,
    val longitude: Float,
    val registeredAt: ZonedDateTime
)

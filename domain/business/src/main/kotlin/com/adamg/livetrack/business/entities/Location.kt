package com.adamg.livetrack.business.entities

import org.threeten.bp.Instant

data class Location(
    val latitude: Double,
    val longitude: Double,
    val time: Instant
)

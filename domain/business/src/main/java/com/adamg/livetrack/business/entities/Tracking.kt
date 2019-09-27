package com.adamg.livetrack.business.entities

sealed class Tracking {

    object InActive : Tracking()

    data class Active(
        val track: Track
    ) : Tracking()
}

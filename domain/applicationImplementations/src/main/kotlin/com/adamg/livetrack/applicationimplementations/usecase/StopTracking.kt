package com.adamg.livetrack.applicationimplementations.usecase

import com.adamg.livetrack.applicationinterfaces.localsource.TrackLocalSource
import com.adamg.livetrack.applicationinterfaces.service.LocationService
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import javax.inject.Inject

class StopTracking @Inject constructor(
    private val locationService: LocationService,
    private val trackLocalSource: TrackLocalSource,
    private val clock: Clock
) : UseCase.Action<Long> {

    override suspend fun execute(param: Long) {
        locationService.stop()
        trackLocalSource.finishTrack(trackId = param, finishedAt = Instant.now(clock))
    }
}

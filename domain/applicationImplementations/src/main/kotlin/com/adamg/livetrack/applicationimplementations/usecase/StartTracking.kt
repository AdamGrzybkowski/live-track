package com.adamg.livetrack.applicationimplementations.usecase

import com.adamg.livetrack.applicationinterfaces.localsource.TrackLocalSource
import com.adamg.livetrack.applicationinterfaces.service.LocationService
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import javax.inject.Inject

class StartTracking @Inject constructor(
    private val locationService: LocationService,
    private val trackLocalSource: TrackLocalSource,
    private val clock: Clock
) : UseCase.Action<Unit> {

    override suspend fun execute(param: Unit) {
        locationService.start()
        trackLocalSource.createTrack(Instant.now(clock))
    }
}

package com.adamg.livetrack.applicationimplementations.usecase

import com.adamg.livetrack.applicationimplementations.repository.TrackRepository
import com.adamg.livetrack.applicationinterfaces.service.LocationService
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import javax.inject.Inject

class StartTracking @Inject constructor(
    private val locationService: LocationService,
    private val trackRepository: TrackRepository,
    private val clock: Clock
) : UseCase.Action<Unit> {

    override suspend fun execute(param: Unit) {
        locationService.start()
        trackRepository.createTrack(Instant.now(clock))
    }
}

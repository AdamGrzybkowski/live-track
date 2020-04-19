package com.adamg.livetrack.applicationimplementations.usecase

import com.adamg.livetrack.applicationimplementations.repository.TrackRepository
import org.threeten.bp.Clock
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class StartTracking @Inject constructor(
    private val trackRepository: TrackRepository,
    private val clock: Clock
) : UseCase.Action<Unit> {

    override suspend fun execute(param: Unit) {
        trackRepository.createTrack(ZonedDateTime.now(clock))
    }
}

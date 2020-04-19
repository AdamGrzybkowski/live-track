package com.adamg.livetrack.applicationimplementations.usecase

import com.adamg.livetrack.applicationimplementations.repository.TrackRepository
import org.threeten.bp.Clock
import org.threeten.bp.ZonedDateTime
import javax.inject.Inject

class StopTracking @Inject constructor(
    private val trackRepository: TrackRepository,
    private val clock: Clock
) : UseCase.Action<Long> {

    override suspend fun execute(param: Long) {
        trackRepository.finishTrack(trackId = param, finishedAt = ZonedDateTime.now(clock))
    }
}

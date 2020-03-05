package com.adamg.livetrack.applicationimplementations.usecase

import com.adamg.livetrack.applicationimplementations.repository.TrackRepository
import com.adamg.livetrack.business.entities.Tracking
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentTrack @Inject internal constructor(
    private val trackRepository: TrackRepository
) : UseCase.Query<Unit, Tracking> {

    override fun execute(): Flow<Tracking> {
        return trackRepository.getTracks()
            .map { tracks ->
                if (tracks.any { it.finishedAt == null }) {
                    Tracking.Active(track = tracks.first { it.finishedAt == null })
                } else {
                    Tracking.InActive
                }
            }
            .distinctUntilChanged()
    }
}

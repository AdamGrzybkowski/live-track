package com.adamg.livetrack.applicationimplementations.usecase

import com.adamg.livetrack.applicationimplementations.repository.TrackRepository
import com.adamg.livetrack.business.entities.Tracking
import io.reactivex.Observable
import javax.inject.Inject

class GetCurrentTrack @Inject internal constructor(
    private val trackRepository: TrackRepository
) : UseCase.Query<Unit, Tracking> {

    override fun execute(): Observable<Tracking> {
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

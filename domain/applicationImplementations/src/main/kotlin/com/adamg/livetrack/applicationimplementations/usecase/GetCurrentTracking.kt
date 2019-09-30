package com.adamg.livetrack.applicationimplementations.usecase

import com.adamg.livetrack.aplicationinterfaces.repository.TrackRepository
import com.adamg.livetrack.aplicationinterfaces.usecase.UseCase
import com.adamg.livetrack.business.entities.Tracking
import io.reactivex.Observable
import javax.inject.Inject

class GetCurrentTracking @Inject constructor(
    private val trackRepository: TrackRepository
) : UseCase.Continous<Unit, Tracking> {

    override fun execute(param: Unit): Observable<Tracking> {
        return trackRepository.getTracks()
            .map { tracks ->
                if (tracks.any { it.finishedAt == null }) {
                    Tracking.Active(track = tracks.first { it.finishedAt == null })
                } else {
                    Tracking.InActive
                }
            }
    }
}

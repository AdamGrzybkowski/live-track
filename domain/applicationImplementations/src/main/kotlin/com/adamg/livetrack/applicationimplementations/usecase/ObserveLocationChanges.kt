package com.adamg.livetrack.applicationimplementations.usecase

import com.adamg.livetrack.applicationimplementations.repository.TrackRepository
import com.adamg.livetrack.applicationinterfaces.service.LocationProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ObserveLocationChanges @Inject constructor(
    private val locationService: LocationProvider,
    private val trackRepository: TrackRepository
) : UseCase.Query<Unit, Unit> {

    override fun execute(): Flow<Unit> {
        return locationService.observeLocationChanges()
            .onEach { location ->
                trackRepository.getLiveTrack().first()?.let { track ->
                    trackRepository.addTrackPoint(
                        trackId = track.id,
                        latitude = location.latitude,
                        longitude = location.longitude,
                        registeredAt = location.time
                    )
                }
            }
            .map { Unit }
    }
}

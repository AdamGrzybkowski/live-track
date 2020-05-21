package com.adamg.livetrack.applicationimplementations.usecase

import com.adamg.livetrack.applicationinterfaces.localsource.TrackLocalSource
import com.adamg.livetrack.applicationinterfaces.service.LocationProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class ObserveLocationChanges @Inject constructor(
    private val locationService: LocationProvider,
    private val trackLocalSource: TrackLocalSource
) : UseCase.Query<Unit, Unit> {

    override fun execute(): Flow<Unit> {
        return locationService.observeLocationChanges()
            .onEach { location ->
                trackLocalSource.getLiveTrack().first()?.let { track ->
                    trackLocalSource.addTrackPoint(
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

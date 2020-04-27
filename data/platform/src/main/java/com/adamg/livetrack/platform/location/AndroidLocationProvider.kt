package com.adamg.livetrack.platform.location

import android.content.Context
import android.location.Location
import android.os.Looper
import com.adamg.livetrack.applicationinterfaces.CoroutinesContextFacade
import com.adamg.livetrack.applicationinterfaces.service.LocationProvider
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import org.threeten.bp.Instant
import javax.inject.Inject
import com.adamg.livetrack.business.entities.Location as DomainLocation

@OptIn(ExperimentalCoroutinesApi::class)
internal class AndroidLocationProvider @Inject constructor(
    context: Context,
    private val contextFacade: CoroutinesContextFacade
) : LocationProvider {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    private val locationRequest = LocationRequest.create()?.apply {
        interval = INTERVAL
        fastestInterval = FASTEST_INTERVAL
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    override fun observeLocationChanges(): Flow<DomainLocation> = callbackFlow<DomainLocation> {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.locations.orEmpty().forEach { location ->
                    offer(location.toDomainLocation())
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        ).addOnFailureListener {
            close()
        }

        awaitClose { fusedLocationClient.removeLocationUpdates(locationCallback) }
    }.flowOn(contextFacade.io)

    companion object {
        private const val INTERVAL = 2000L
        private const val FASTEST_INTERVAL = 1000L
    }
}

private fun Location.toDomainLocation(): DomainLocation {
    return DomainLocation(
        latitude = latitude,
        longitude = longitude,
        time = Instant.ofEpochMilli(time)
    )
}

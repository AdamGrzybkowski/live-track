package com.adamg.livetrack.presentation.bindingadapters

import androidx.databinding.BindingAdapter
import com.adamg.livetrack.business.entities.TrackPoint
import com.adamg.livetrack.business.entities.Tracking
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions

private const val MAP_ZOOM = 16f

@BindingAdapter("tracking")
fun MapView.bindTracking(tracking: Tracking?) {
    when (tracking) {
        is Tracking.Active -> {
            getMapAsync { map ->
                PolylineOptions().apply {
                    addAll(tracking.track.points.map { it.toLatLng })
                }.let { polylineOptions -> map.addPolyline(polylineOptions) }

                tracking.track.points.lastOrNull()?.let { lastPoint ->
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastPoint.toLatLng, MAP_ZOOM))
                }
            }
        }
        Tracking.InActive -> {
            getMapAsync { map ->
                map.clear()
            }
        }
    }
}

private val TrackPoint.toLatLng: LatLng
    get() = LatLng(latitude, longitude)

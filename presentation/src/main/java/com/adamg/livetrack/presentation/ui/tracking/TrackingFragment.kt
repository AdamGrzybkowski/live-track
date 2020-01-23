package com.adamg.livetrack.presentation.ui.tracking

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adamg.livetrack.presentation.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class TrackingFragment : BaseFragment<TrackingViewModel>(), OnMapReadyCallback, EasyPermissions.PermissionCallbacks {

    companion object {
        private const val RC_FINE_LOCATION = 123
    }

    override val viewModelClass = TrackingViewModel::class

    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_tracking_fragmnet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapTracking) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        requireLocationPermission()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) = Unit

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    @AfterPermissionGranted(RC_FINE_LOCATION)
    private fun requireLocationPermission() {
        if (EasyPermissions.hasPermissions(requireContext(), ACCESS_FINE_LOCATION)) {
            map.isMyLocationEnabled = true
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.permission_location_rationale),
                RC_FINE_LOCATION,
                ACCESS_FINE_LOCATION
            )
        }
    }
}

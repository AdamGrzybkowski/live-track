package com.adamg.livetrack.presentation.ui.tracking

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.adamg.livetrack.presentation.R
import com.adamg.livetrack.presentation.databinding.FragmentTrackingBinding
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class TrackingFragment : BaseFragment<TrackingViewModel, FragmentTrackingBinding>(), EasyPermissions.PermissionCallbacks {

    companion object {
        private const val RC_FINE_LOCATION = 123
    }

    override val viewModelClass = TrackingViewModel::class
    override val layoutId = R.layout.fragment_tracking

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.onCreate(savedInstanceState)
        requireLocationPermission()

        viewModel.getViewState().observe(viewLifecycleOwner, Observer { viewState ->
            viewState.tracking?.let { tracking ->
                binding.tracking = tracking
            }
        })
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
            binding.mapView.getMapAsync { map -> map.isMyLocationEnabled = true }
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

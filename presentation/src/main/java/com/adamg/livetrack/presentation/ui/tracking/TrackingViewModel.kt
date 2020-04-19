package com.adamg.livetrack.presentation.ui.tracking

import androidx.lifecycle.viewModelScope
import com.adamg.livetrack.applicationimplementations.usecase.GetTracking
import com.adamg.livetrack.applicationimplementations.usecase.StartTracking
import com.adamg.livetrack.applicationimplementations.usecase.StopTracking
import com.adamg.livetrack.business.entities.Tracking
import com.adamg.livetrack.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrackingViewModel @Inject constructor(
    getCurrentTracking: GetTracking,
    private val startTracking: StartTracking,
    private val stopTracking: StopTracking
) : BaseViewModel<TrackingViewState>(TrackingViewState()) {

    init {
        viewModelScope.launch {
            getCurrentTracking.execute().collect { tracking ->
                setState { copy(tracking = tracking) }
            }
        }
    }

    fun onPlayPauseClicked() {
        viewModelScope.launch {
            when (val tracking = getViewState().value?.tracking) {
                Tracking.InActive -> startTracking.execute(Unit)
                is Tracking.Active -> stopTracking.execute(tracking.track.id)
                null -> Unit
            }
        }
    }
}

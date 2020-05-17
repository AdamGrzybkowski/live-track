package com.adamg.livetrack.presentation.ui.tracking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adamg.livetrack.applicationimplementations.usecase.GetTracking
import com.adamg.livetrack.applicationimplementations.usecase.StartTracking
import com.adamg.livetrack.applicationimplementations.usecase.StopTracking
import com.adamg.livetrack.business.entities.Tracking
import com.adamg.livetrack.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class TrackingViewModel @Inject constructor(
    getCurrentTracking: GetTracking,
    private val startTracking: StartTracking,
    private val stopTracking: StopTracking
) : BaseViewModel<TrackingViewState>(TrackingViewState()) {

    val trackingButtonState = MutableLiveData<TrackingViewState.TrackButtonState>()

    init {
        getCurrentTracking.execute()
            .onEach { tracking -> setState { copy(tracking = tracking) } }
            .distinctUntilChangedBy { it::class }
            .onEach { tracking -> trackingButtonState.value = tracking.buttonState }
            .launchIn(viewModelScope)
    }

    fun onPlayStopClicked() {
        viewModelScope.launch {
            when (val tracking = getViewState().value?.tracking) {
                Tracking.InActive -> startTracking.execute(Unit)
                is Tracking.Active -> stopTracking.execute(tracking.track.id)
            }
        }
    }
}

private val Tracking.buttonState: TrackingViewState.TrackButtonState
    get() = when (this) {
        Tracking.InActive -> TrackingViewState.TrackButtonState.PLAY
        is Tracking.Active -> TrackingViewState.TrackButtonState.STOP
    }

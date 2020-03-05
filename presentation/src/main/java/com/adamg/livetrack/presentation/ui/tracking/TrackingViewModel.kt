package com.adamg.livetrack.presentation.ui.tracking

import androidx.lifecycle.viewModelScope
import com.adamg.livetrack.applicationimplementations.usecase.GetCurrentTrack
import com.adamg.livetrack.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrackingViewModel @Inject constructor(
    getCurrentTracking: GetCurrentTrack
) : BaseViewModel<TrackingViewState>(TrackingViewState()) {

    init {
        viewModelScope.launch {
            getCurrentTracking.execute().collect { tracking ->
                setState { copy(tracking = tracking) }
            }
        }
    }

    fun onPlayPauseClicked() {
    }
}

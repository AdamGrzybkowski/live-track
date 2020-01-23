package com.adamg.livetrack.presentation.ui.tracking

import com.adamg.livetrack.business.entities.Tracking
import com.adamg.livetrack.presentation.ui.base.ViewState

data class TrackingViewState(
    val tracking: Tracking? = null
) : ViewState

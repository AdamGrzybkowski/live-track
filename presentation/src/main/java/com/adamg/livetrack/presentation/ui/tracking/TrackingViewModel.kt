package com.adamg.livetrack.presentation.ui.tracking

import com.adamg.livetrack.applicationimplementations.usecase.GetCurrentTrack
import com.adamg.livetrack.presentation.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class TrackingViewModel @Inject constructor(
    getCurrentTracking: GetCurrentTrack
) : BaseViewModel<TrackingViewState>(TrackingViewState()) {

    init {
        getCurrentTracking.execute()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = { setState { copy(tracking = it) } }
            ).addTo(compositeDisposable)
    }

    fun onPlayPauseClicked() {

    }
}

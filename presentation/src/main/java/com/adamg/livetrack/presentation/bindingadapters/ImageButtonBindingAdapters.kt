package com.adamg.livetrack.presentation.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.adamg.livetrack.presentation.R
import com.adamg.livetrack.presentation.ui.tracking.TrackingViewState
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("trackingButtonState")
fun FloatingActionButton.setupButton(trackButtonState: TrackingViewState.TrackButtonState?) {
    when (trackButtonState) {
        TrackingViewState.TrackButtonState.PLAY -> {
            AnimatedVectorDrawableCompat.create(context, R.drawable.ic_stop_play)?.let {
                setImageDrawable(it)
                it.start()
            }
            visibility = View.VISIBLE
        }
        TrackingViewState.TrackButtonState.STOP -> {
            AnimatedVectorDrawableCompat.create(context, R.drawable.ic_play_stop)?.let {
                setImageDrawable(it)
                it.start()
            }
            visibility = View.VISIBLE
        }
        null -> {
            visibility = View.GONE
        }
    }
}

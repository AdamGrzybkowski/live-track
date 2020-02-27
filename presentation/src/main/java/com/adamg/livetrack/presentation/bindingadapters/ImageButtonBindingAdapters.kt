package com.adamg.livetrack.presentation.bindingadapters

import android.view.View
import android.widget.ImageButton
import androidx.databinding.BindingAdapter
import com.adamg.livetrack.business.entities.Tracking
import com.adamg.livetrack.presentation.R

@BindingAdapter("app:tracking")
fun setupButton(view: ImageButton, tracking: Tracking?) {
    when (tracking) {
        Tracking.InActive -> {
            view.setBackgroundResource(R.drawable.button_background_green_circle)
            view.setImageResource(R.drawable.ic_start)
            view.visibility = View.VISIBLE
        }
        is Tracking.Active -> {
            view.setBackgroundResource(R.drawable.button_background_red_circle)
            view.setImageResource(R.drawable.ic_stop)
            view.visibility = View.VISIBLE
        }
        else -> {
            view.visibility = View.GONE
        }
    }
}


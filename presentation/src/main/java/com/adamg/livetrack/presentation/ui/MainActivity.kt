package com.adamg.livetrack.presentation.ui

import android.os.Bundle
import com.adamg.livetrack.presentation.R
import dagger.android.DaggerActivity

class MainActivity : DaggerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

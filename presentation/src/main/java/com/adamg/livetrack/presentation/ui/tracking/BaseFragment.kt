package com.adamg.livetrack.presentation.ui.tracking

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.adamg.livetrack.presentation.ui.base.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<V : ViewModel> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<TrackingViewModel>

    abstract val viewModelClass: KClass<V>

    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = viewModelFactory.create(viewModelClass.java)
    }
}

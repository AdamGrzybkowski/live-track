package com.adamg.livetrack.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<T : ViewState>(viewState: T) : ViewModel() {

    private val viewState = MutableLiveData(viewState)

    fun getViewState(): LiveData<T> = viewState

    protected fun setState(transformation: T.() -> T) {
        viewState.value = viewState.value?.transformation()
    }
}

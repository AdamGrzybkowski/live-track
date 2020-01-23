package com.adamg.livetrack.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T : ViewState>(viewState: T) : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    private val viewState = MutableLiveData(viewState)

    fun getViewState(): LiveData<T> = viewState

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    protected fun setState(transformation: T.() -> T) {
        viewState.value = viewState.value?.transformation()
    }
}

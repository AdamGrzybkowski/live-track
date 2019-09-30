package com.adamg.livetrack.aplicationinterfaces.usecase

import io.reactivex.Completable
import io.reactivex.Observable

interface UseCase {

    interface Action<in TParam> {

        fun execute(param: TParam): Completable
    }

    interface Continous<in TParam, TResult> : UseCase {

        fun execute(param: TParam): Observable<TResult>
    }
}

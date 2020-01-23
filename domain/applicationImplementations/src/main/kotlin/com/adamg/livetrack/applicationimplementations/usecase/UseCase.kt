package com.adamg.livetrack.applicationimplementations.usecase

import io.reactivex.Completable
import io.reactivex.Observable

internal interface UseCase {

    interface Action<in TParam> {

        fun execute(param: TParam): Completable
    }

    interface Query<in TParam, TResult> : UseCase {

        fun execute(): Observable<TResult>
    }
}
